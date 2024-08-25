package pks.example.modernize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
@Table(name = "EX_PROGRAM")
public class LoanProgram {
    @Id
    @Column(name = "C_CODE")
    private String code;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_DESCRIPTION")
    private String description;
}
