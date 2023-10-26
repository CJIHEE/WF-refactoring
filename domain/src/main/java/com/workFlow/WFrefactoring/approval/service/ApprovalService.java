package com.workFlow.WFrefactoring.approval.service;

import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;

    public void saveAll(List<Approval> approvals) {
        approvalRepository.saveAll(approvals);
    }

    public List<Approval> findByDocumentWithApprover(Document document) {
        return approvalRepository.findByDocumentWithApprover(document);
    }
}
