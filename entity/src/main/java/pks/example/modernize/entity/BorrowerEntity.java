package pks.example.modernize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "EX_BORROWER")
public class BorrowerEntity {

    @EmbeddedId
    BorrowerEntityPk pk;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_PHONE")
    private String phone;

    @Column(name = "C_ORGANIZATION")
    private String organization;

}
