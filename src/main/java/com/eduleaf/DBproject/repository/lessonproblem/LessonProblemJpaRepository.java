package com.eduleaf.DBproject.repository.lessonproblem;

import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.LessonProblem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonProblemJpaRepository extends JpaRepository<LessonProblem, Integer> {
    Optional<List<LessonProblem>> findLessonProblemByLesson(Lesson lesson);
}
