package com.eduleaf.DBproject.repository.group;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Teacher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupJpaRepository extends JpaRepository<Group, Long> {
    Optional<Group> findGroupByName(String name);

    @Query("SELECT g FROM Group g WHERE g.teacher = :teacher")
    List<Group> findGroupsByTeacher(Teacher teacher);
}
