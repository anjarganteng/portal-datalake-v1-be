package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class CounterRateSbsRequestDTO implements Serializable {

    private static final long serialVersionUID = 979466181570341905L;

    private String jenisInstrumen;
    private String jenisValuta;
    private String jangkaWaktu;
    private BigDecimal rateMin;
    private BigDecimal rateMax;
    private boolean isNew;

    public String getJenisInstrumen() {
        return jenisInstrumen;
    }

    public void setJenisInstrumen(String jenisInstrumen) {
        this.jenisInstrumen = jenisInstrumen.toUpperCase();
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta.toUpperCase();
    }

    public String getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(String jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu.toUpperCase();
    }

    public BigDecimal getRateMin() {
        return rateMin;
    }

    public void setRateMin(BigDecimal rateMin) {
        this.rateMin = rateMin;
    }

    public BigDecimal getRateMax() {
        return rateMax;
    }

    public void setRateMax(BigDecimal rateMax) {
        this.rateMax = rateMax;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
