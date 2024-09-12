package pks.example.modernize.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanProgramRepository extends JpaRepository<LoanProgramEntity,String> {
    
    List<LoanProgramEntity> findByName(String name);
    List<LoanProgramEntity> findByNameContaining(String name);
    List<LoanProgramEntity> findByDescription(String name);
    List<LoanProgramEntity> findByDescriptionContaining(String name);

    @Query("SELECT pgm FROM LoanProgramEntity pgm WHERE pgm.name LIKE %:chars% OR pgm.description LIKE %:chars%")
    List<LoanProgramEntity> findAnyMatching(String chars);
}
