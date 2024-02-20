package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedIntercompanyTransactionKbank implements Serializable {

    @Column(name = "account_type")
    private String accountType;
    
    @Column(name = "account_no")
    private String accountNo;

    public EmbedIntercompanyTransactionKbank() {
    }

    public EmbedIntercompanyTransactionKbank(String accountType, String accountNo) {
        this.accountType = accountType;
        this.accountNo = accountNo;
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
    
}