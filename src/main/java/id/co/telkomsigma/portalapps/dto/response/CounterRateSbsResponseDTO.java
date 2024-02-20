package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.CounterRateSbs;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class CounterRateSbsResponseDTO {

    private static final long serialVersionUID = 9028164370106021530L;

    private String jenisInstrumen;
    private String jenisValuta;
    private String jangkaWaktu;
    private BigDecimal rateMin;
    private BigDecimal rateMax;

    public CounterRateSbsResponseDTO() {
    }

    public CounterRateSbsResponseDTO(CounterRateSbs counterRateSbs) {
        jenisInstrumen = counterRateSbs.getEmbedRateSbs().getJenisInstrumen();
        jenisValuta = counterRateSbs.getEmbedRateSbs().getJenisValuta();
        jangkaWaktu = counterRateSbs.getEmbedRateSbs().getJangkaWaktu();
        rateMin = counterRateSbs.getRateMin();
        rateMax = counterRateSbs.getRateMax();
    }

    public String getJenisInstrumen() {
        return jenisInstrumen;
    }

    public void setJenisInstrumen(String jenisInstrumen) {
        this.jenisInstrumen = jenisInstrumen;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }

    public String getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(String jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu;
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
}
