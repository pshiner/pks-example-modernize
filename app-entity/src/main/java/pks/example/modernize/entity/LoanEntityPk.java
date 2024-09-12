package pks.example.modernize.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable @Getter @Setter
public class LoanEntityPk implements Serializable {

    @Column(name = "FK_PROGRAM_CODE")
    private String program;

    @Column(name = "C_CODE")
    private String code;

    public LoanEntityPk() {
    }

    public LoanEntityPk(String code, String program) {
        this.code = code;
        this.program = program;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, program);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;       
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        LoanEntityPk other = (LoanEntityPk)obj;
        return this.code.equals(other.code) && this.program.equals(other.program);
    }
}
