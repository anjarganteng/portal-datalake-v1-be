package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.CounterRateSbk;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class CounterRateSbkResponseDTO {

    private static final long serialVersionUID = 859391193907724700L;

    private String jenisPenggunaan;
    private String jenisValuta;
    private BigDecimal rateFlat;
    private BigDecimal rateEfektif;

    public CounterRateSbkResponseDTO() {
    }

    public CounterRateSbkResponseDTO(CounterRateSbk counterRateSbk) {
        jenisPenggunaan = counterRateSbk.getEmbedRateSbk().getJenisPenggunaan();
        jenisValuta = counterRateSbk.getEmbedRateSbk().getJenisValuta();
        rateFlat = counterRateSbk.getRateFlat();
        rateEfektif = counterRateSbk.getRateEfektif();
    }

    public String getJenisPenggunaan() {
        return jenisPenggunaan;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
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
}
