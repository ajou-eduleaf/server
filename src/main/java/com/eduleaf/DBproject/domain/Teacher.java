package com.eduleaf.DBproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false)
    private String teachId;

    @Column(length = 20, nullable = false)
    private String teachPw;

    @Column(length = 10, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;

    @Builder
    public Teacher(String teachId, String teachPw, String name, Academy academy) {
        this.teachId = teachId;
        this.teachPw = teachPw;
        this.name = name;
        this.academy = academy;
    }
}
