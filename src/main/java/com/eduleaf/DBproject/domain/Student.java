package com.eduleaf.DBproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false)
    private String studentId;

    @Column(length = 20, nullable = false)
    private String studentPw;

    @Column(length = 20, nullable = false)
    private String bojId;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private final List<StudentLesson> studentLessons = new ArrayList<>();

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Parent parent;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private final List<StudentProblem> studentProblems = new ArrayList<>();

    @Builder
    public Student(String studentId, String studentPw, String bojId, String name, int age, Group group, Academy academy, Parent parent) {
        this.studentId = studentId;
        this.studentPw = studentPw;
        this.bojId = bojId;
        this.name = name;
        this.age = age;
        this.group = group;
        this.academy = academy;
        this.parent = parent;
    }
}
