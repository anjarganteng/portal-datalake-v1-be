package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Lps;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class LpsResponseDTO {

    private static final long serialVersionUID = 1679196108292786500L;

    private String tglMulai;
    private String tglJt;
    private String jenisValuta;
    private BigDecimal sukuBunga;

    public LpsResponseDTO() {
    }

    public LpsResponseDTO(Lps lps) {
        tglMulai = String.valueOf(lps.getEmbedLps().getTglMulai());
        tglJt = lps.getTglJt();
        jenisValuta = lps.getEmbedLps().getJenisValuta();
        sukuBunga = lps.getSukuBunga();
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getTglJt() {
        return tglJt;
    }

    public void setTglJt(String tglJt) {
        this.tglJt = tglJt;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }

    public BigDecimal getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(BigDecimal sukuBunga) {
        this.sukuBunga = sukuBunga;
    }
}
