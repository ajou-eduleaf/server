package com.eduleaf.DBproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserLoginFormDto {
    private final String id;
    private final String pw;
    private final String academyName;
}
