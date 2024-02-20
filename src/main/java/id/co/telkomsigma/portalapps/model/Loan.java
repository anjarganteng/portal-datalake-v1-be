package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedLoan;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_loan_bot")
public class Loan {
    @EmbeddedId
    protected EmbedLoan embedLoan;
    
    @Column(name = "movement_type")
    private String movementType;
    
    @Column(name = "operation_progress")
    private String operationProgress;
    
    @Column(name = "asset_class_reason")
    private String assetClassReason;
    
    @Column(name = "asset_class_reason_unused")
    private String assetClassReasonUnused;
    
    @Column(name = "tdr_type")
    private String tdrType;
    
    @Column(name = "tdr_method_type")
    private String tdrMethodType;
    
    @Column(name = "loan_type")
    private String loanType;
    
    @Column(name = "revolving")
    private String revolving;
    
    @Column(name = "collateral_desc")
    private String collateralDesc;

    public Loan() {
    }

    public Loan(EmbedLoan embedLoan) {
        this.embedLoan = embedLoan;
    }

    public Loan(String customerNo, String loanNo) {
        this.embedLoan = new EmbedLoan(customerNo, loanNo);
    }

    public EmbedLoan getEmbedLoan() {
        return embedLoan;
    }

    public void setEmbedLoan(EmbedLoan embedLoan) {
        this.embedLoan = embedLoan;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getOperationProgress() {
        return operationProgress;
    }

    public void setOperationProgress(String operationProgress) {
        this.operationProgress = operationProgress;
    }

    public String getAssetClassReason() {
        return assetClassReason;
    }

    public void setAssetClassReason(String assetClassReason) {
        this.assetClassReason = assetClassReason;
    }

    public String getAssetClassReasonUnused() {
        return assetClassReasonUnused;
    }

    public void setAssetClassReasonUnused(String assetClassReasonUnused) {
        this.assetClassReasonUnused = assetClassReasonUnused;
    }

    public String getTdrType() {
        return tdrType;
    }

    public void setTdrType(String tdrType) {
        this.tdrType = tdrType;
    }

    public String getTdrMethodType() {
        return tdrMethodType;
    }

    public void setTdrMethodType(String tdrMethodType) {
        this.tdrMethodType = tdrMethodType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getRevolving() {
        return revolving;
    }

    public void setRevolving(String revolving) {
        this.revolving = revolving;
    }

    public String getCollateralDesc() {
        return collateralDesc;
    }

    public void setCollateralDesc(String collateralDesc) {
        this.collateralDesc = collateralDesc;
    }
    
}
