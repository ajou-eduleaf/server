package com.eduleaf.DBproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RankResponseDto {

    private String bojId;
    private Integer solved;

    @Builder
    public RankResponseDto(String bojId, Integer solved){
        this.bojId = bojId;
        this.solved = solved;
    }
}
