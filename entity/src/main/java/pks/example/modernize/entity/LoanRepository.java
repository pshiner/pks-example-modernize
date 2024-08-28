package pks.example.modernize.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity,LoanEntityPk> {
    // public LoanEntity findByLoanEntityId(LoanEntityId loanEntityId);
    // public void deleteByLoanEntityId(LoanEntityId loanEntityId);
}
