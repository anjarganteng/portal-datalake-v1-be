package id.co.telkomsigma.portalapps.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_post_employment_benefits_employee_id_bot")
public class PostEmploymentBenefitsEmployeeId {
    @Id
    @Column(name = "description")
    private String description;

    @Column(name = "31_mar")
    private BigDecimal mar;

    @Column(name = "30_jun")
    private BigDecimal jun;

    @Column(name = "30_sep")
    private BigDecimal sep;

    @Column(name = "31_dec")
    private BigDecimal dec;

    public PostEmploymentBenefitsEmployeeId() {
    }

    public PostEmploymentBenefitsEmployeeId(String description, BigDecimal mar, BigDecimal jun, BigDecimal sep, BigDecimal dec) {
        this.description = description;
        this.mar = mar;
        this.jun = jun;
        this.sep = sep;
        this.dec = dec;
    }
    
    public PostEmploymentBenefitsEmployeeId(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMar() {
        return mar;
    }

    public void setMar(BigDecimal mar) {
        this.mar = mar;
    }

    public BigDecimal getJun() {
        return jun;
    }

    public void setJun(BigDecimal jun) {
        this.jun = jun;
    }

    public BigDecimal getSep() {
        return sep;
    }

    public void setSep(BigDecimal sep) {
        this.sep = sep;
    }

    public BigDecimal getDec() {
        return dec;
    }

    public void setDec(BigDecimal dec) {
        this.dec = dec;
    }
    
}
