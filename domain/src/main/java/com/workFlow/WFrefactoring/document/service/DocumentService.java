package com.workFlow.WFrefactoring.document.service;

import com.workFlow.WFrefactoring.approval.service.ApprovalService;
import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.dept.service.DeptService;
import com.workFlow.WFrefactoring.document.dto.ApprovalServiceDto;
import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentResponse;
import com.workFlow.WFrefactoring.document.dto.DocumentServiceDto;
import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.exception.DocumentNotFoundException;
import com.workFlow.WFrefactoring.model.*;
import com.workFlow.WFrefactoring.repository.DocumentRepository;
import com.workFlow.WFrefactoring.utils.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.workFlow.WFrefactoring.enums.ApprovalProgress.WAITING;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final EmployeeService employeeService;
    private final DocumentRepository documentRepository;
    private final DeptService deptService;
    private final ApprovalService approvalService;
    private final FileManager fileManager;

    //문서 작성
    @Transactional
    public DocumentResponse writeDocument(DocumentServiceDto.WriteDocument documentDto, AttachmentServiceDto.UploadAttachment attachmentDto) throws IOException {
        Employee employee = employeeService.findEmployee(documentDto.getRequester());
        Document document = documentRepository.save(documentDto.toDocument(employee));

        // 파일 업로드(StreamAPI)
        List<Attachment> attachments = attachmentDto.getMultipartFileList().stream().map(
                multipartFile -> new Attachment(
                        document,
                        fileManager.uploadFile(multipartFile),
                        multipartFile.getOriginalFilename(),
                        multipartFile.getSize()
                )
        ).collect(Collectors.toList());

        document.getAttachmentList().addAll(attachments);

        //결제라인 조회
        List<DeptDto> approverList = deptService.createAppDeptList(employee.getDeptNo());
        
        //결제담당 empNo 추출
        List<Long> leadEmpNoList = extractLeadEmpNo(approverList);

        // key => EmpNo(Long) value => level
        Map<Long, Integer> levelMap = generateLevelMap(leadEmpNoList);

        //employee 조회 정렬(In절로 한번에 조회하기 위해서 leadEmpNoList 추출)
        List<Employee> leadEmployees = employeeService.findEmployeeList(leadEmpNoList);

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
        approvalService.saveAll(approvals);
        return DocumentResponse.from(document,extractAttList(document));
    }


    private List<Long> extractLeadEmpNo(List<DeptDto> approverList) {
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


    //특정 문서 조회
    public DocumentResponse.getDocumentResponse getDocument(Long docNo) {
        DocumentResponse.getDocumentResponse response = new DocumentResponse.getDocumentResponse();
        Document document = documentRepository.findById(docNo).orElseThrow(()-> new DocumentNotFoundException("document not found"));
        List<Approval> approvalList = approvalService.findByDocumentWithApprover(document);

        return response.from(document, extractAttList(document),approvalConvertDto(approvalList));
    }


    //response AttachmentList 추출
    private List<String> extractAttList(Document document){
        return document.getAttachmentList().stream()
                .map(i -> i.getFileName())
                .collect(Collectors.toList());

    }

    private List<ApprovalServiceDto.getApproval> approvalConvertDto(List<Approval> approvalList) {
        ApprovalServiceDto.getApproval approvalDto = new ApprovalServiceDto.getApproval();
        return  approvalList.stream().map(approvalDto::of).collect(Collectors.toList());
    }


    //문서 전체 조회
    public List<DocumentResponse.getDocumentListResponse> getAllDocument(Long lastDocNo, Integer pageSize) {
        if(pageSize == null) pageSize = 5;

        List<Document> documentList = documentRepository.findDocumentAll(lastDocNo, pageSize);

        return documentList.stream()
                .map(DocumentResponse.getDocumentListResponse::from)
                .collect(Collectors.toList());
    }


}
