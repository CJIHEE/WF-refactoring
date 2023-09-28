package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.workFlow.WFrefactoring.model.Document;

import java.util.List;

public interface DocumentRepositoryCustom {

    List<Document> findDocumentAll(Long lastDocNo, Integer pageSize);
}
