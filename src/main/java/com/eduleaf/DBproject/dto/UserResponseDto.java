package com.eduleaf.DBproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String name;
    private UserType type; // UserType은 enum 타입입니다.
    private String academyName;
    private String groupName;

    @Builder
    public UserResponseDto(String id, String name, UserType type, String academyName, String groupName) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.academyName = academyName == null ? "" : academyName;
        this.groupName = groupName;
    }

    public enum UserType {
        teacher, parent, student
    }
}
