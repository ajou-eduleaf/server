package com.eduleaf.DBproject.repository.group;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Teacher;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepository {

    private final GroupJpaRepository groupJpaRepository;

    public GroupRepository(GroupJpaRepository groupJpaRepository) {
        this.groupJpaRepository = groupJpaRepository;
    }

    public void save(Group group){
        groupJpaRepository.save(group);
    }

    public Optional<Group> findGroupByName(String name){
        return groupJpaRepository.findGroupByName(name);
    }

    public List<Group> findGroupsByTeacher(Teacher teacher){
        return groupJpaRepository.findGroupsByTeacher(teacher);
    }
}

