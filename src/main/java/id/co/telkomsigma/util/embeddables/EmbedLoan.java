package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedLoan implements Serializable {

    @Column(name = "customer_no")
    private String customerNo;
    
    @Column(name = "loan_no")
    private String loanNo;

    public EmbedLoan() {
    }

    public EmbedLoan(String customerNo, String loanNo) {
        this.customerNo = customerNo;
        this.loanNo = loanNo;
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

}