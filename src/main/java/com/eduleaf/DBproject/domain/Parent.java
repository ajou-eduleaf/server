package com.eduleaf.DBproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parent")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String phoneNumber;

    @Column(length = 10)
    private String relation;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Builder
    public Parent(String name, String phoneNumber, String relation, Student student) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.relation = relation == null ? "" : relation;
        this.student = student;
    }
}
