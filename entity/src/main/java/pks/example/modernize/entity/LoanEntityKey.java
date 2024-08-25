package pks.example.modernize.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class LoanEntityKey implements Serializable {
    @Id
    @Column(name = "FK_PROGRAM_CODE")
    private String program;

    @Id
    @Column(name = "N_LOAN_NUMBER")
    private Long id;
}
