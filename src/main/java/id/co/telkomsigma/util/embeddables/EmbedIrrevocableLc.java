package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedIrrevocableLc implements Serializable {

    @Column(name = "customer_no")
    private String customerNo;
    
    @Column(name = "account_no")
    private String accountNo;

    public EmbedIrrevocableLc() {
    }

    public EmbedIrrevocableLc(String customerNo, String accountNo) {
        this.customerNo = customerNo;
        this.accountNo = accountNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    
}
