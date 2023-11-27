package com.eduleaf.DBproject.repository.lesson;

import com.eduleaf.DBproject.domain.Lesson;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonJpaRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findById(int lessonId);
}
