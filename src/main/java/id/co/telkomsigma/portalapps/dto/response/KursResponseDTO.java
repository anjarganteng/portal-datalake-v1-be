package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Kurs;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class KursResponseDTO {

    private static final long serialVersionUID = 1825866637544596951L;

    private String mataUang;
    private String tanggal;
    private BigDecimal kursJual;
    private BigDecimal kursBeli;
    private String jenis;

    public KursResponseDTO() {
    }

    public KursResponseDTO(Kurs kurs) {
        mataUang = kurs.getEmbedKurs().getMataUang();
        tanggal = kurs.getEmbedKurs().getTanggal();
        jenis = kurs.getEmbedKurs().getJenis();
        kursJual = kurs.getKursJual();
        kursBeli = kurs.getKursBeli();
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getKursJual() {
        return kursJual;
    }

    public void setKursJual(BigDecimal kursJual) {
        this.kursJual = kursJual;
    }

    public BigDecimal getKursBeli() {
        return kursBeli;
    }

    public void setKursBeli(BigDecimal kursBeli) {
        this.kursBeli = kursBeli;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
