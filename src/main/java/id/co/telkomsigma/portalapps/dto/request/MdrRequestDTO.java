package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class MdrRequestDTO implements Serializable {

    private static final long serialVersionUID = -5171248927572105335L;

    private String kodeMerchant;
    private BigDecimal rate;
    private boolean isNew;

    public String getKodeMerchant() {
        return kodeMerchant;
    }

    public void setKodeMerchant(String kodeMerchant) {
        this.kodeMerchant = kodeMerchant.toUpperCase();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
