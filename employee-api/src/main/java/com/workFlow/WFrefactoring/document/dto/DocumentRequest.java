package com.workFlow.WFrefactoring.document.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class DocumentRequest {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "기안 문서 작성 Request Parameter")
    public static class WriteDocument {
        @NotNull
        @ApiModelProperty(value = "문서 작성 사원 번호", required = true, example = "53")
        private long requester;
        @NotBlank(message = "필수값입니다.")
        @ApiModelProperty(value = "문서 제목", required = true, example = "기안 문서 결제")
        private String subject;
        @NotBlank(message = "필수값입니다.")
        @ApiModelProperty(value = "문서 내용", required = true, example = "결제 바랍니다")
        private String contents;
        @NotBlank(message = "필수값입니다.")
        @ApiModelProperty(value = "결제 마감일", required = true, example = "2023-04-12")
        private String expiredAt;
        @NotNull(message = "필수값입니다.")
        @ApiModelProperty(value = "문서 승인 여부", required = true, example = "true")
        private boolean complete;
    }
}
