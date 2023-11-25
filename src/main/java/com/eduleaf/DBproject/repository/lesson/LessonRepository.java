package com.eduleaf.DBproject.repository.lesson;

import com.eduleaf.DBproject.domain.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public class LessonRepository {

    private final LessonJpaRepository lessonJpaRepository;

    public LessonRepository(LessonJpaRepository lessonJpaRepository) {
        this.lessonJpaRepository = lessonJpaRepository;
    }

    public void save(Lesson lesson) {
        lessonJpaRepository.save(lesson);
    }
}
