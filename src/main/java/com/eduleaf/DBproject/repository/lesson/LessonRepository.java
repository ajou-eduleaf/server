package com.eduleaf.DBproject.repository.lesson;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Lesson;
import java.util.List;
import java.util.Optional;
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
    public Optional<Lesson> findById(int lessonId){
        return lessonJpaRepository.findById(lessonId);
    }

    public Optional<List<Lesson>> findAllByGroup(Group group){
        return lessonJpaRepository.findAllByGroup(group);
    }
}
