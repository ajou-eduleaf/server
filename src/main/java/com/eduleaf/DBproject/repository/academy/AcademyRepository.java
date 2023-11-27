package com.eduleaf.DBproject.repository.academy;

import com.eduleaf.DBproject.domain.Academy;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AcademyRepository{

    private final AcademyJpaRepository academyJpaRepository;

    public AcademyRepository(AcademyJpaRepository academyJpaRepository) {
        this.academyJpaRepository = academyJpaRepository;
    }
    public void save(Academy academy){
        academyJpaRepository.save(academy);
    }

    public Optional<Academy> findById(Integer id){
        return academyJpaRepository.findById(id);
    }

    public List<Academy> findAll(){
        return academyJpaRepository.findAll();
    }

    public Optional<Academy> findByAcademyName(String academyName){
        return academyJpaRepository.findAcademyByName(academyName);
    }
}
