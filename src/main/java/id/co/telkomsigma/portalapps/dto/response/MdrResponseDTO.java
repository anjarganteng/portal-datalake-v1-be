package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Mdr;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class MdrResponseDTO {

    private static final long serialVersionUID = 6745813314303940124L;

    private String kodeMerchant;
    private BigDecimal rate;

    public MdrResponseDTO() {
    }

    public MdrResponseDTO(Mdr mdr) {
        kodeMerchant = mdr.getKodeMerchant();
        rate = mdr.getRate();
    }

    public String getKodeMerchant() {
        return kodeMerchant;
    }

    public void setKodeMerchant(String kodeMerchant) {
        this.kodeMerchant = kodeMerchant;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
