package pks.example.modernize.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "EX_LOAN")
public class LoanEntity {

    @EmbeddedId
    LoanEntityPk pk;

    @Column(name = "FK_BORROWER_CODE")
    private String borrowerCode;

    @Column(name = "N_LOAN_NUMBER")
    private Integer loanNumber;

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

    @Column(name = "FK_APP_USER")
    private String appUser;

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
    public int hashCode() {
        return pk.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) 
            return false;

        LoanEntity other = (LoanEntity)obj;
        return (this.pk.equals(other.pk));
    }

}
