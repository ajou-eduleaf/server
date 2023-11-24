package com.eduleaf.DBproject.repository.teacher;

import com.eduleaf.DBproject.domain.Teacher;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {

    private final TeacherJpaRepository teacherJpaRepository;

    public TeacherRepository(TeacherJpaRepository teacherJpaRepository) {
        this.teacherJpaRepository = teacherJpaRepository;
    }

    public void save(Teacher teacher){
        teacherJpaRepository.save(teacher);
    }

    public Optional<Teacher> findTeacherByTeacherId(String teacherId){
        return teacherJpaRepository.findTeacherByTeachId(teacherId);
    }
}

