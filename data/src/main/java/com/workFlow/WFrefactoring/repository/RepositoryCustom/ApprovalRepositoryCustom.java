package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprovalRepositoryCustom {
    List<Approval> findByDocumentWithApprover(Document document);
}
