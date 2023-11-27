package com.eduleaf.DBproject.repository.group;

import com.eduleaf.DBproject.domain.Group;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJpaRepository extends JpaRepository<Group, Long> {
    Optional<Group> findGroupByName(String name);
}
