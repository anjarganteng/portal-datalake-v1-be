package id.co.telkomsigma.portalapps.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit 
 */
@Entity
@Table(name = "tabel_summary_adjustment_bot")
public class SummaryAdjustment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUMMARY_ADJUSTMENT_ID")
    private Long summaryAdjustmentId;
    
    @Column(name = "ADJUSTMENT_NO")
    private String adjustmentNo;

    @Column(name = "COA")
    private String coa;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DEBIT_CREDIT")
    private BigDecimal debitCredit;
    
    @Column(name = "NOTE")
    private String note;
    
    @Column(name = "FLAG")
    private String flag;

    public SummaryAdjustment() {
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
