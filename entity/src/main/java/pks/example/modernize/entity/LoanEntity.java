package pks.example.modernize.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
@Table(name = "EX_LOAN")
@IdClass(LoanEntityKey.class)
public class LoanEntity {

    @Id
    @Column(name = "FK_PROGRAM_CODE")
    private String program;

    @Id
    @Column(name = "N_LOAN_NUMBER")
    private Long id;

    @Column(name = "C_CODE")
    private String code;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_DESCRIPTION")
    private String description;

    @Column(name = "BD_INTEREST_RATE")
    private BigDecimal rate;

    @Column(name = "BD_ORIGINAL_PRINCIPAL")
    private BigDecimal amount;

}
