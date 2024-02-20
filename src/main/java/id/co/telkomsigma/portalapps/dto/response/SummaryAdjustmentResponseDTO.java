package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.SummaryAdjustment;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class SummaryAdjustmentResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private Long summaryAdjustmentId;
    private String adjustmentNo;
    private String coa;
    private String description;
    private BigDecimal debitCredit;
    private String note;
    private String flag;

    public SummaryAdjustmentResponseDTO() {
    }

    public SummaryAdjustmentResponseDTO(SummaryAdjustment summaryAdjustment) {
        this.summaryAdjustmentId = summaryAdjustment.getSummaryAdjustmentId();
        this.adjustmentNo = summaryAdjustment.getAdjustmentNo();
        this.coa = summaryAdjustment.getCoa();
        this.description = summaryAdjustment.getDescription();
        this.debitCredit = summaryAdjustment.getDebitCredit();
        this.note = summaryAdjustment.getNote();
        this.flag = summaryAdjustment.getFlag();
    }

    public Long getSummaryAdjustmentId() {
        return summaryAdjustmentId;
    }

    public void setSummaryAdjustmentId(Long summaryAdjustmentId) {
        this.summaryAdjustmentId = summaryAdjustmentId;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }    
}