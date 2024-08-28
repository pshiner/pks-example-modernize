package pks.example.modernize.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "EX_LOAN")
// @IdClass(LoanEntityId.class)
public class LoanEntity {

    // @Id
    // @Column(name = "FK_PROGRAM_CODE")
    // private String program;

    // @Id
    // @Column(name = "N_LOAN_NUMBER")
    // private Long id;

    @EmbeddedId
    LoanEntityId loanId;

    @NaturalId
    @Column(name = "C_CODE")
    private String code;

    @Column(name = "C_STATUS")
    private String status;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_DESCRIPTION")
    private String description;

    @Column(name = "BD_INTEREST_RATE")
    private BigDecimal rate;

    @Column(name = "BD_ORIGINAL_PRINCIPAL")
    private BigDecimal amount;

    // public LoanEntityId getLoanEntityId() {
    //     return new LoanEntityId(id, program);
    // }

    // public void setLoanEntityId(LoanEntityId loanId) {
    //     this.id = loanId.getId();
    //     this.program = loanId.program();
    // }

    public LoanEntity() {
        this.status = "D";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) 
            return false;

        LoanEntity other = (LoanEntity)obj;
        return (this.loanId.equals(other.loanId));
    }

}
