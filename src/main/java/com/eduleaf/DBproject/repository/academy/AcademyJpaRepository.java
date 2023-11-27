package com.eduleaf.DBproject.repository.academy;

import com.eduleaf.DBproject.domain.Academy;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyJpaRepository extends JpaRepository<Academy, Integer> {
    Optional<Academy> findAcademyByName(String academyName);
}
