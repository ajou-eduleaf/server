package com.eduleaf.DBproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseMessageDto {
    private final String message;

    @Builder
    public ResponseMessageDto(String message) {
        this.message = message;
    }
}
