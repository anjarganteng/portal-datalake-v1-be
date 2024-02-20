package id.co.telkomsigma.portalapps.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_short_term_employee_benefits_bot")
public class ShortTermEmployeeBenefits {

    @Id
    @Column(nullable = false)
    private String description;
    
    @Column(name = "jan_mar")
    private BigDecimal janMar;
    
    @Column(name = "apr_jun")
    private BigDecimal aprJun;
    
    @Column(name = "jul_sep")
    private BigDecimal julSep;
    
    @Column(name = "oct_dec")
    private BigDecimal octDec;

    public ShortTermEmployeeBenefits() {
    }

    public ShortTermEmployeeBenefits(String description, BigDecimal janMar, BigDecimal aprJun, BigDecimal julSep, BigDecimal octDec) {
        this.description = description;
        this.janMar = janMar;
        this.aprJun = aprJun;
        this.julSep = julSep;
        this.octDec = octDec;
    }
    
    public ShortTermEmployeeBenefits(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getJanMar() {
        return janMar;
    }

    public void setJanMar(BigDecimal janMar) {
        this.janMar = janMar;
    }

    public BigDecimal getAprJun() {
        return aprJun;
    }

    public void setAprJun(BigDecimal aprJun) {
        this.aprJun = aprJun;
    }

    public BigDecimal getJulSep() {
        return julSep;
    }

    public void setJulSep(BigDecimal julSep) {
        this.julSep = julSep;
    }

    public BigDecimal getOctDec() {
        return octDec;
    }

    public void setOctDec(BigDecimal octDec) {
        this.octDec = octDec;
    }

}
