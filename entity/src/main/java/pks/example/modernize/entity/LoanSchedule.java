package pks.example.modernize.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
 @Table(name = "EX_PERIODIC_PAYMENT")
public class LoanSchedule {

    @EmbeddedId
    private LoanSchedulePk pk;

    @Column(name = "D_PMT_DATE")
    private Date pmtDate;

    @Column(name = "D_PMTADJUSTED_DATE")
    private Date pmtAdjustedDate;

    @Column(name = "BD_PMT_PRINCIPAL")
    private BigDecimal pmtPrincipal;

    @Column(name = "BD_PMT_INTEREST")
    private BigDecimal pmtInterest;

    @Column(name = "BD_PMT_CAP_INTEREST")
    private BigDecimal pmtCapitalizedInterest;

    @Column(name = "BD_BAL_PRINCIPAL")
    private BigDecimal balPrincipal;

    @Column(name = "BD_BAL_CAP_INTEREST")
    private BigDecimal balCapitalizedInterest;

    @Override 
    public int hashCode() {
        return this.pk.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;       
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        return this.pk.equals(obj);
    }

}
