package com.workFlow.WFrefactoring.document;

import com.workFlow.WFrefactoring.document.dto.DocumentRequest;
import com.workFlow.WFrefactoring.document.dto.DocumentResponse;
import com.workFlow.WFrefactoring.document.service.DocumentService;
import com.workFlow.WFrefactoring.document.mapper.ServiceDtoMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {
    private final DocumentService documentService;
    //문서 작성
    @ApiImplicitParams({@ApiImplicitParam(name = "files", value = "첨부 파일",dataType = "List", paramType = "formData"),@ApiImplicitParam(name = "request", value = "writeDocument")})

    @PostMapping
    public DocumentResponse writeDocument(@RequestPart(value="request") @Valid DocumentRequest.WriteDocument request,
                                          @RequestPart(name ="files" , value="files", required = false) List<MultipartFile> multipartFile) throws IOException {
        return documentService.writeDocument(
                ServiceDtoMapper.mapping(request),
                ServiceDtoMapper.mapping(multipartFile)
        );
    }

    //특정 문서 조회
    @GetMapping("/{docNo}")
    @ApiImplicitParam(name = "docNo", value = "문서 번호",dataType = "Long", paramType = "path")
    public DocumentResponse.getDocumentResponse getDocument(@PathVariable("docNo") Long docNo){
        return documentService.getDocument(docNo);
    }

    //전체 문서 조회
    @GetMapping
    @ApiImplicitParams({@ApiImplicitParam(name = "lastDocNo", value = "마지막 조회 문서 번호", dataType = "Long", paramType = "query")
            , @ApiImplicitParam(name = "pageSize", value = "조회 문서 수", dataType = "Integer", paramType = "query", defaultValue = "3")})
    public List<DocumentResponse.getDocumentListResponse> getAllDocument(@RequestParam(name = "lastDocNo") Long lastDocNo,@RequestParam(name = "pageSize", required = false)Integer pageSize ){
        return documentService.getAllDocument(lastDocNo, pageSize);
    }



}
