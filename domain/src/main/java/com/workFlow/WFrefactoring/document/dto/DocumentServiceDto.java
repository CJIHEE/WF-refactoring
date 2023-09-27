package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class DocumentServiceDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WriteDocument{
        @NotNull
        private long requester;
        @NotNull
        private String subject;
        @NotNull
        private String contents;
        @NotNull
        private String expiredAt;
        @NotNull
        private boolean complete;

        private LocalDateTime modifiedAt;

        public Document toDocument(Employee employee){
            return Document.builder()
                    .requester(employee)
                    .subject(this.subject)
                    .contents(this.contents)
                    .expiredAt(this.expiredAt)
                    .complete(this.complete)
                    .modifiedAt(this.modifiedAt)
                    .attachmentList(new ArrayList<>())
                    .build();
        }

        // Factory Method



    }
}
