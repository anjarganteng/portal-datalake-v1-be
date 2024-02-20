package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedIntercompanyTransactionKbank;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_intercompany_transaction_kbank_bot")
public class IntercompanyTransactionKbank {

    @EmbeddedId
    protected EmbedIntercompanyTransactionKbank embedIntercompanyTransactionKbank;
    
    @Column(name = "branch_code")
    private String branchCode;
    
    @Column(name = "ogl_account_no")
    private String oglAccountNo;
    
    @Column(name = "ogl_amount")
    private BigDecimal oglAmount;
    
    @Column(name = "bank_statement_amount")
    private BigDecimal bankStatementAmount;
    
    @Column(name = "remain_period")
    private String remainPeriod;

    public IntercompanyTransactionKbank() {
    }

    public IntercompanyTransactionKbank(EmbedIntercompanyTransactionKbank Kbank) {
        this.embedIntercompanyTransactionKbank = embedIntercompanyTransactionKbank;
    }

    public IntercompanyTransactionKbank(String accountType, String accountNo) {
        this.embedIntercompanyTransactionKbank = new EmbedIntercompanyTransactionKbank(accountType, accountNo);
    }

    public EmbedIntercompanyTransactionKbank getEmbedIntercompanyTransactionKbank() {
        return embedIntercompanyTransactionKbank;
    }

    public void setEmbedIntercompanyTransactionKbank(EmbedIntercompanyTransactionKbank embedIntercompanyTransactionKbank) {
        this.embedIntercompanyTransactionKbank = embedIntercompanyTransactionKbank;
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
    
}
