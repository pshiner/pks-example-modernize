package pks.example.modernize.entity;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.internal.util.type.PrimitiveWrapperHelper.IntegerDescriptor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Embeddable @Getter @Setter
public class LoanEntityId implements Serializable {
    // @Id
    @Column(name = "FK_PROGRAM_CODE")
    private String program;

    // @Id
    @Column(name = "N_LOAN_NUMBER")
    private Integer id;

    public LoanEntityId() {
    }

    public LoanEntityId(Integer id, String program) {
        this.id = id;
        this.program = program;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, program);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;       
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        LoanEntityId other = (LoanEntityId)obj;
        return id.equals(other.id) && program.equals(other.program);
    }
}

// public record LoanEntityId (
//     Long id, 
//     String program) { // properties need to be in alphabetic order because of hibernate

// }
