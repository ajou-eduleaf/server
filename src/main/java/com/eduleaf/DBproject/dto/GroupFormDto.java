package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Teacher;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupFormDto {
    private final String name;
    private final String academyName;
    private final String teacherName;

    @Builder
    public Group toEntity(Academy academy, Teacher teacher){
        return Group.builder()
                .name(name)
                .teacher(teacher)
                .academy(academy)
                .build();
    }
}
