package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Parent;
import com.eduleaf.DBproject.domain.Student;
import lombok.Builder;
import lombok.Data;

@Data
public class ParentSignUpFormDto {

    private final String name;
    private final String parentId;
    private final String parentPw;
    private final String phoneNumber;
    private final String relation;
    private final String studentId;

    @Builder
    public Parent toEntity(Student student){
        return Parent.builder()
                .name(name)
                .parentId(parentId)
                .parentPw(parentPw)
                .phoneNumber(phoneNumber == null ? "" : phoneNumber)
                .relation(relation)
                .student(student)
                .build();
    }
}
