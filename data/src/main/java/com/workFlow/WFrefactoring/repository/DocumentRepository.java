package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.repository.RepositoryCustom.DocumentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long>, DocumentRepositoryCustom {
}
