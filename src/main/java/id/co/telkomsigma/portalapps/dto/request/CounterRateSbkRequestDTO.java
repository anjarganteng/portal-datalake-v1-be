package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class CounterRateSbkRequestDTO implements Serializable {

    private static final long serialVersionUID = -587728834503266151L;

    private String jenisPenggunaan;
    private String jenisValuta;
    private BigDecimal rateFlat;
    private BigDecimal rateEfektif;
    private boolean isNew;

    public String getJenisPenggunaan() {
        return jenisPenggunaan;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan.toUpperCase();
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta.toUpperCase();
    }

    public BigDecimal getRateFlat() {
        return rateFlat;
    }

    public void setRateFlat(BigDecimal rateFlat) {
        this.rateFlat = rateFlat;
    }

    public BigDecimal getRateEfektif() {
        return rateEfektif;
    }

    public void setRateEfektif(BigDecimal rateEfektif) {
        this.rateEfektif = rateEfektif;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
