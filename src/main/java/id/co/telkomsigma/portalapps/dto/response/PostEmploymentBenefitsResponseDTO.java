package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.PostEmploymentBenefits;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class PostEmploymentBenefitsResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String description;
    private BigDecimal janMar;
    private BigDecimal aprJun;
    private BigDecimal julSep;
    private BigDecimal octDec;

    public PostEmploymentBenefitsResponseDTO() {
    }

    public PostEmploymentBenefitsResponseDTO(PostEmploymentBenefits benefits) {
        this.description = benefits.getDescription();
        this.janMar = benefits.getJanMar();
        this.aprJun = benefits.getAprJun();
        this.julSep = benefits.getJulSep();
        this.octDec = benefits.getOctDec();
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
