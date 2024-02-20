package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.IntercompanyTransactionGroup;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class IntercompanyTransactionGroupResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String accountType;
    private String accountNo;
    private String branchCode;
    private String oglAccountNo;
    private BigDecimal oglAmount;
    private BigDecimal bankStatementAmount;
    private String remainPeriod;
    private String intercompanyCode;

    public IntercompanyTransactionGroupResponseDTO() {
    }

    public IntercompanyTransactionGroupResponseDTO(IntercompanyTransactionGroup transactionGroup) {
        this.accountType = transactionGroup.getEmbedIntercompanyTransactionGroup().getAccountType();
        this.accountNo = transactionGroup.getEmbedIntercompanyTransactionGroup().getAccountNo();
        this.branchCode = transactionGroup.getBranchCode();
        this.oglAccountNo = transactionGroup.getOglAccountNo();
        this.oglAmount = transactionGroup.getOglAmount();
        this.bankStatementAmount = transactionGroup.getBankStatementAmount();
        this.remainPeriod = transactionGroup.getRemainPeriod();
        this.intercompanyCode = transactionGroup.getIntercompanyCode();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getOglAccountNo() {
        return oglAccountNo;
    }

    public void setOglAccountNo(String oglAccountNo) {
        this.oglAccountNo = oglAccountNo;
    }

    public BigDecimal getOglAmount() {
        return oglAmount;
    }

    public void setOglAmount(BigDecimal oglAmount) {
        this.oglAmount = oglAmount;
    }

    public BigDecimal getBankStatementAmount() {
        return bankStatementAmount;
    }

    public void setBankStatementAmount(BigDecimal bankStatementAmount) {
        this.bankStatementAmount = bankStatementAmount;
    }

    public String getRemainPeriod() {
        return remainPeriod;
    }

    public void setRemainPeriod(String remainPeriod) {
        this.remainPeriod = remainPeriod;
    }

    public String getIntercompanyCode() {
        return intercompanyCode;
    }

    public void setIntercompanyCode(String intercompanyCode) {
        this.intercompanyCode = intercompanyCode;
    }
    
    
}
