package com.eduleaf.DBproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "academy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, nullable = false)
    private String name;

    @OneToMany(mappedBy = "academy", fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "academy", fetch = FetchType.LAZY)
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "academy", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
}
