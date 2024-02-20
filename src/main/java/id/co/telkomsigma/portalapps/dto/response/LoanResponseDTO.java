package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Loan;

/**
 *
 * @author radit
 */
public class LoanResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String customerNo;
    private String loanNo;
    private String movementType;
    private String operationProgress;
    private String assetClassReason;
    private String assetClassReasonUnused;
    private String tdrType;
    private String tdrMethodType;
    private String loanType;
    private String revolving;
    private String collateralDesc;

    public LoanResponseDTO() {
    }

    public LoanResponseDTO(Loan loan) {
        this.customerNo = loan.getEmbedLoan().getCustomerNo();
        this.loanNo = loan.getEmbedLoan().getLoanNo();
        this.movementType = loan.getMovementType();
        this.operationProgress = loan.getOperationProgress();
        this.assetClassReason = loan.getAssetClassReason();
        this.assetClassReasonUnused = loan.getAssetClassReasonUnused();
        this.tdrType = loan.getTdrType();
        this.tdrMethodType = loan.getTdrMethodType();
        this.loanType = loan.getLoanType();
        this.revolving = loan.getRevolving();
        this.collateralDesc = loan.getCollateralDesc();
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
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
