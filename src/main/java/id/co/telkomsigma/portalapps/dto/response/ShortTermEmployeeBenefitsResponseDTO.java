package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ShortTermEmployeeBenefits;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class ShortTermEmployeeBenefitsResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String description;
    private BigDecimal janMar;
    private BigDecimal aprJun;
    private BigDecimal julSep;
    private BigDecimal octDec;

    public ShortTermEmployeeBenefitsResponseDTO() {
    }

    public ShortTermEmployeeBenefitsResponseDTO(ShortTermEmployeeBenefits employeeBenefits) {
        this.description = employeeBenefits.getDescription();
        this.janMar = employeeBenefits.getJanMar();
        this.aprJun = employeeBenefits.getAprJun();
        this.julSep = employeeBenefits.getJulSep();
        this.octDec = employeeBenefits.getOctDec();
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
