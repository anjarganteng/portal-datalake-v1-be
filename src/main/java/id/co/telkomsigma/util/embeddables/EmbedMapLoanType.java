package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedMapLoanType implements Serializable {

    @Column(name = "loan_type")
    private String loanType;
    
    @Column(name = "bmi_product")
    private String bmiProduct;

    public EmbedMapLoanType() {
    }

    public EmbedMapLoanType(String loanType, String bmiProduct) {
        this.loanType = loanType;
        this.bmiProduct = bmiProduct;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getBmiProduct() {
        return bmiProduct;
    }

    public void setBmiProduct(String bmiProduct) {
        this.bmiProduct = bmiProduct;
    }
    
}