package pks.example.modernize.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoanSchedulePk {

    @Column(name = "FK_PROGRAM_CODE")
    private String programCode;

    @Column(name = "FK_LOAN_CODE")
    private String loanCode;

    @Column(name = "N_PMT_SEQUENCE")
    private Integer sequence;

    @Override
    public int hashCode() {
        return Objects.hash(programCode,loanCode,sequence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) 
            return false;

        LoanSchedulePk other = (LoanSchedulePk) obj;
        return this.programCode.equals(other.programCode) &&
                this.loanCode.equals(other.loanCode) &&
                this.sequence.equals(other.sequence);
    }

}
