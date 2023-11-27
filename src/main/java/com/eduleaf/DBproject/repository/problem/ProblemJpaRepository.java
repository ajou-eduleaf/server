package com.eduleaf.DBproject.repository.problem;

import com.eduleaf.DBproject.domain.Problem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemJpaRepository extends JpaRepository<Problem, String> {
}
