package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Academy;
import lombok.Data;

@Data
public class AcademyFormDto {
    String name;

    public AcademyFormDto(String name) {
        this.name = name;
    }

    public Academy toEntity(){
        return Academy.builder()
                .name(name)
                .build();
    }
}
