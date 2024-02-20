package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedIntercompanyTransactionGroup;
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
@Table(name = "tabel_intercompany_transaction_group_bot")
public class IntercompanyTransactionGroup {

    @EmbeddedId
    protected EmbedIntercompanyTransactionGroup embedIntercompanyTransactionGroup;
    
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
    
    @Column(name = "intercompany_code")
    private String intercompanyCode;

    public IntercompanyTransactionGroup() {
    }

    public IntercompanyTransactionGroup(EmbedIntercompanyTransactionGroup tabelIntercompanyTransactionGroupBotPK) {
        this.embedIntercompanyTransactionGroup = tabelIntercompanyTransactionGroupBotPK;
    }

    public IntercompanyTransactionGroup(String accountType, String accountNo) {
        this.embedIntercompanyTransactionGroup = new EmbedIntercompanyTransactionGroup(accountType, accountNo);
    }

    public EmbedIntercompanyTransactionGroup getEmbedIntercompanyTransactionGroup() {
        return embedIntercompanyTransactionGroup;
    }

    public void setEmbedIntercompanyTransactionGroup(EmbedIntercompanyTransactionGroup embedIntercompanyTransactionGroup) {
        this.embedIntercompanyTransactionGroup = embedIntercompanyTransactionGroup;
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
