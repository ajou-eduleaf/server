package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.Group;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupFormDto {
    private final String name;
    private final String academyName;

    @Builder
    public Group toEntity(Academy academy){
        return Group.builder()
                .name(name)
                .academy(academy)
                .build();
    }
}
