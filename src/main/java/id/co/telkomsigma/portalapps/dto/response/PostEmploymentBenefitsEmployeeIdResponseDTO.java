package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.PostEmploymentBenefitsEmployeeId;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author radit
 */
public class PostEmploymentBenefitsEmployeeIdResponseDTO {    
    private static final long serialVersionUID = 1L;
    
    private String description;
    private BigDecimal mar;
    private BigDecimal jun;
    private BigDecimal sep;
    private BigDecimal dec;

    public PostEmploymentBenefitsEmployeeIdResponseDTO() {
    }

    public PostEmploymentBenefitsEmployeeIdResponseDTO(PostEmploymentBenefitsEmployeeId benefitsEmployeeId) {
        this.description = benefitsEmployeeId.getDescription();
        this.mar = benefitsEmployeeId.getMar();
        this.jun = benefitsEmployeeId.getJun();
        this.sep = benefitsEmployeeId.getSep();
        this.dec = benefitsEmployeeId.getDec();
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
