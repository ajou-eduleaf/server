package com.eduleaf.DBproject.repository.problem;

import com.eduleaf.DBproject.domain.Problem;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProblemRepository {

    private final ProblemJpaRepository problemJpaRepository;

    public ProblemRepository(ProblemJpaRepository problemJpaRepository) {
        this.problemJpaRepository = problemJpaRepository;
    }

    public Optional<Problem> findById(String problemId) {
        return problemJpaRepository.findById(problemId);
    }

    public void save(Problem problem) {
        problemJpaRepository.save(problem);
    }
}
