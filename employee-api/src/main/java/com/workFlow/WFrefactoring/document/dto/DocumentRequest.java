package com.workFlow.WFrefactoring.document.dto;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class DocumentRequest {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WriteDocument {
        @NotNull
        private long requester;
        @NotBlank(message = "필수값입니다.")
        private String subject;
        @NotBlank(message = "필수값입니다.")
        private String contents;
        @NotBlank(message = "필수값입니다.")
        private String expiredAt;
        @NotNull(message = "필수값입니다.")
        private boolean complete;
    }
}
