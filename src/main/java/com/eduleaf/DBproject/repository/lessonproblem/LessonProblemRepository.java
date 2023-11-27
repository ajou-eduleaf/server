package com.eduleaf.DBproject.repository.lessonproblem;

import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.LessonProblem;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class LessonProblemRepository {

    private final LessonProblemJpaRepository lessonProblemJpaRepository;

    public LessonProblemRepository(LessonProblemJpaRepository lessonProblemJpaRepository) {
        this.lessonProblemJpaRepository = lessonProblemJpaRepository;
    }

    public void save(LessonProblem lessonProblem) {
        lessonProblemJpaRepository.save(lessonProblem);
    }

    public Optional<List<LessonProblem>> findLessonProblemByLesson(Lesson lesson) {
        return lessonProblemJpaRepository.findLessonProblemByLesson(lesson);
    }
}
