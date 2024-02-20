package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.AdjustmentThb;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class AdjustmentThbResponseDTO {
    
    private static final long serialVersionUID = 1L;
    
    private Long adjustmentThbId;
    private String adjustmentNo;
    private String coa;
    private String description;
    private BigDecimal debitCredit;
    private String note;

    public AdjustmentThbResponseDTO() {
    }

    public AdjustmentThbResponseDTO(AdjustmentThb adjustmentThb) {
        this.adjustmentThbId = adjustmentThb.getAdjustmentThbId();
        this.adjustmentNo = adjustmentThb.getAdjustmentNo();
        this.coa = adjustmentThb.getCoa();
        this.description = adjustmentThb.getDescription();
        this.debitCredit = adjustmentThb.getDebitCredit();
        this.note = adjustmentThb.getNote();
    }

    public Long getAdjustmentThbId() {
        return adjustmentThbId;
    }

    public void setAdjustmentThbId(Long adjustmentThbId) {
        this.adjustmentThbId = adjustmentThbId;
    }
    
    public String getAdjustmentNo() {
        return adjustmentNo;
    }

    public void setAdjustmentNo(String adjustmentNo) {
        this.adjustmentNo = adjustmentNo;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(BigDecimal debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
   
}
