package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedMapLoanType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_map_loan_type_bot")
public class MapLoanType {

    @EmbeddedId
    protected EmbedMapLoanType embedMapLoanType;
    
    @Column(name = "kbank_product")
    private String kbankProduct;

    public MapLoanType() {
    }

    public MapLoanType(EmbedMapLoanType embedMapLoanType) {
        this.embedMapLoanType = embedMapLoanType;
    }

    public MapLoanType(String loanType, String bmiProduct) {
        this.embedMapLoanType = new EmbedMapLoanType(loanType, bmiProduct);
    }

    public EmbedMapLoanType getEmbedMapLoanType() {
        return embedMapLoanType;
    }

    public void setEmbedMapLoanType(EmbedMapLoanType embedMapLoanType) {
        this.embedMapLoanType = embedMapLoanType;
    }

    public String getKbankProduct() {
        return kbankProduct;
    }

    public void setKbankProduct(String kbankProduct) {
        this.kbankProduct = kbankProduct;
    }

}
