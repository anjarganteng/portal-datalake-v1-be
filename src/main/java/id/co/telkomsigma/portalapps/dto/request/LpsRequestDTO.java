package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class LpsRequestDTO implements Serializable {

    private static final long serialVersionUID = -1599946628930110279L;

    private String tglMulai;
    private String tglJt;
    private String jenisValuta;
    private BigDecimal sukuBunga;
    private boolean isNew;

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai.toUpperCase();
    }

    public String getTglJt() {
        return tglJt;
    }

    public void setTglJt(String tglJt) {
        this.tglJt = tglJt.toUpperCase();
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta.toUpperCase();
    }

    public BigDecimal getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(BigDecimal sukuBunga) {
        this.sukuBunga = sukuBunga;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
