package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    //조회
    List<Approval> findAllByDocument(Document document);

    @Query("SELECT a FROM Approval a WHERE a.document.docNo = :docNo")
    List<Approval> findByDocNo(@Param("docNo") Long docNo);

    List<Approval> findByDocument(Document document);

    @Query("SELECT a FROM Approval a JOIN FETCH a.approver WHERE a.document = :document")
    List<Approval> findByDocumentWithApprover(@Param("document") Document document);




}
