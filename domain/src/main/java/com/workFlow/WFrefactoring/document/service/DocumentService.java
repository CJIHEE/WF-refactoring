package com.workFlow.WFrefactoring.document.service;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentResponse;
import com.workFlow.WFrefactoring.document.dto.DocumentServiceDto;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.exception.UserNotFoundException;
import com.workFlow.WFrefactoring.model.*;
import com.workFlow.WFrefactoring.repository.ApprovalRepository;
import com.workFlow.WFrefactoring.repository.DeptRepository;
import com.workFlow.WFrefactoring.repository.DocumentRepository;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.repository.RepositoryCustom.DeptNativeRepository;
import com.workFlow.WFrefactoring.utils.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.workFlow.WFrefactoring.enums.ApprovalProgress.WAITING;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final EmployeeRepository employeeRepository;
    private final DocumentRepository documentRepository;
    private final DeptRepository deptRepository;
    private final ApprovalRepository approvalRepository;
    private final DeptNativeRepository deptNativeRepository;
    private final FileManager fileManager;

    //문서 작성
    @Transactional
    public DocumentResponse writeDocument(DocumentServiceDto.WriteDocument documentDto, AttachmentServiceDto.UploadAttachment attachmentDto) throws IOException {

        Employee employee = employeeRepository.findById(documentDto.getRequester()).orElseThrow(() -> new UserNotFoundException("user not found"));
        Document document = documentRepository.save(documentDto.toDocument(employee));

        List<MultipartFile> multipartFileList = attachmentDto.getMultipartFileList();

        if (multipartFileList != null) {
            //첨부파일 있을 때
            attachmentDto = fileManager.uploadFile(multipartFileList);
            for(int i=0; i < multipartFileList.size(); i++){
                Attachment attachment = attachmentDto.toAttachment(document, i);
                document.getAttachmentList().add(attachment);
            }
        }

        //결제라인 조회
        List<Dept> approverList = createAppDeptList(deptRepository.findById(employee.getDeptNo())
                .orElseThrow(()-> new DeptNotFoundException("dept not found")).getDeptNo());

//        nativeQuery 썼을 때
//        List<Dept> approverList = deptNativeRepository.findApprovarList(deptRepository.findById(employee.getDeptNo())
//                .orElseThrow(()-> new DeptNotFoundException("dept not found")).getDeptNo());

        //결제담당 empNo 추출
        List<Long> leadEmpNoList = extractLeadEmpNo(approverList);

        // key => EmpNo(Long) value => level
        Map<Long, Integer> levelMap = generateLevelMap(leadEmpNoList);

        //employee 조회 정렬(In절로 한번에 조회하기 위해서 leadEmpNoList 추출)
        List<Employee> leadEmployees = employeeRepository.findByEmpNoIn(leadEmpNoList);

        List<Approval> approvals = new ArrayList<>();
        for (Employee emp : leadEmployees) {
            Approval approval = Approval.builder()
                    .approver(emp)
                    .document(document)
                    .levelNo(levelMap.get(emp.getEmpNo()))
                    .approval(WAITING)
                    .build();

            approvals.add(approval);
        }

        approvalRepository.saveAll(approvals);
        return DocumentResponse.from(document,extractAttList(document));
    }

    private List<Dept> createAppDeptList(Integer deptNo) {
        List<Dept> deptList = deptRepository.findAllByOrderByDeptNoDesc();
        List<Dept> appDeptList = new ArrayList();
        Integer targetDeptNo = deptNo;
        for(Dept dept : deptList){
            if(targetDeptNo == dept.getDeptNo()){
                appDeptList.add(dept);
                targetDeptNo = dept.getUpperDeptNo();
            }
        }
        return appDeptList;
    }

    private List<Long> extractLeadEmpNo(List<Dept> approverList) {
        return approverList.stream()
                .map(dept -> dept.getLeadEmpNo())
                .collect(Collectors.toList());
    }

    /**
     * Map 생성
     * Key => 결정권자 사원번호, Value => Level
     */
    private Map<Long, Integer> generateLevelMap(List<Long> leadEmpNoList) {
        Map<Long, Integer> map = new HashMap<>();

        int level = leadEmpNoList.size();
        for (Long empNo : leadEmpNoList) {
            map.put(empNo, level);
            level--;
        }
        return map;
    }

    //response AttachmentList 추출
    private List<String> extractAttList(Document document){
        return document.getAttachmentList().stream()
                .map(i -> i.getFileName())
                .collect(Collectors.toList());

    }


    //문서 조회
//    public DocumentResponse.getDocumentResponse getDocument(Long docNo) {
//        DocumentResponse.getDocumentResponse response = new DocumentResponse.getDocumentResponse();
//
//
//        return response.toVO();
//    }
}
