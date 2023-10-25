package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.model.QApproval;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.workFlow.WFrefactoring.model.QApproval.*;

@Repository
@RequiredArgsConstructor
public class ApprovalRepositoryImpl implements ApprovalRepositoryCustom{
    final JPAQueryFactory queryFactory;
    @Override
    public List<Approval> findByDocumentWithApprover(Document document) {
        return queryFactory
                .selectFrom(approval1)
                .where(approval1.document.docNo.eq(document.getDocNo()))
                .fetch();
    }
}
