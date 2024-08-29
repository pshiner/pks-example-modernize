package pks.example.modernize.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable @Getter @Setter
public class LoanSchedulePk {

    @Column(name = "FK_BORROWER_CODE")
    private String borrowerCode;

    @Column(name = "FK_LOAN_CODE")
    private String loanCode;

    @Column(name = "N_PMT_SEQUENCE")
    private Integer sequence;

    @Override
    public int hashCode() {
        return Objects.hash(borrowerCode,loanCode,sequence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) 
            return false;

        LoanSchedulePk other = (LoanSchedulePk) obj;
        return this.borrowerCode.equals(other.borrowerCode) &&
                this.loanCode.equals(other.loanCode) &&
                this.sequence.equals(other.sequence);
    }

}
