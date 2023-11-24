package com.eduleaf.DBproject.repository.parent;

import com.eduleaf.DBproject.domain.Parent;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ParentRepository {

    private final ParentJpaRepository parentJpaRepository;

    public ParentRepository(ParentJpaRepository parentJpaRepository) {
        this.parentJpaRepository = parentJpaRepository;
    }

    public Optional<Parent> findParentByParentId(String parentId){
        return parentJpaRepository.findParentByParentId(parentId);
    }

    public void save(Parent parent){
        parentJpaRepository.save(parent);
    }
}
