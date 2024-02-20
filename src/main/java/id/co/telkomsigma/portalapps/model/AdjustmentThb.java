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
@Table(name = "tabel_adjustment_thb_bot")
public class AdjustmentThb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADJUSTMENT_THB_ID")
    private Long adjustmentThbId;
    
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
    
    public AdjustmentThb() {
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
