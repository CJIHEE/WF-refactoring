package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.repository.RepositoryCustom.ApprovalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long>, ApprovalRepositoryCustom {
    //조회
//    @Query("SELECT a FROM Approval a JOIN FETCH a.approver WHERE a.document = :document")
//    List<Approval> findByDocumentWithApprover(@Param("document") Document document);




}
