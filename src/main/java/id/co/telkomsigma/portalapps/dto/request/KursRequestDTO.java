package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class KursRequestDTO implements Serializable {

    private static final long serialVersionUID = 5373721695650428491L;

    private String mataUang;
    private String tanggal;
    private BigDecimal kursJual;
    private BigDecimal kursBeli;
    private String jenis;
    private boolean isNew;

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang.toUpperCase();
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal.toUpperCase();
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
        this.jenis = jenis.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
