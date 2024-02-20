package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class CashbackRequestDTO implements Serializable {

    private static final long serialVersionUID = 2352679579494683616L;

    private String noRek;
    private String jnsSimpanan;
    private BigDecimal nominalCashback;
    private BigDecimal persentaseCashback;
    private boolean isNew;

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek.toUpperCase();
    }

    public String getJnsSimpanan() {
        return jnsSimpanan;
    }

    public void setJnsSimpanan(String jnsSimpanan) {
        this.jnsSimpanan = jnsSimpanan.toUpperCase();
    }

    public BigDecimal getNominalCashback() {
        return nominalCashback;
    }

    public void setNominalCashback(BigDecimal nominalCashback) {
        this.nominalCashback = nominalCashback;
    }

    public BigDecimal getPersentaseCashback() {
        return persentaseCashback;
    }

    public void setPersentaseCashback(BigDecimal persentaseCashback) {
        this.persentaseCashback = persentaseCashback;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
