package com.eduleaf.DBproject.repository.parent;

import com.eduleaf.DBproject.domain.Parent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentJpaRepository extends JpaRepository<Parent , Integer> {
    Optional<Parent> findParentByParentId(String parentId);
}
