package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.model.QDocument;
import com.workFlow.WFrefactoring.model.QEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Document> findDocumentAll(Long lastDocNo, Integer pageSize) {
        QDocument document = QDocument.document;
        return queryFactory
                .selectFrom(document)
                .where(gtDocNo(lastDocNo))
                .limit(pageSize)
                .fetch();
    }

    private BooleanExpression gtDocNo(Long lastDocNo){
        QDocument document = QDocument.document;
        return lastDocNo == null ? null : document.docNo.gt(lastDocNo);
    }
}
