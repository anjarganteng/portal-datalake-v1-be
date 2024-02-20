package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class PtsRequestDTO implements Serializable {

    private static final long serialVersionUID = -678357541526705922L;

    private String sandiAntasena;
    private String deskripsiSandi;
    private String kodeGl;
    private String deskripsiGl;
    private String mataUang;
    private boolean isNew;

    public String getSandiAntasena() {
        return sandiAntasena;
    }

    public void setSandiAntasena(String sandiAntasena) {
        this.sandiAntasena = sandiAntasena.toUpperCase();
    }

    public String getDeskripsiSandi() {
        return deskripsiSandi;
    }

    public void setDeskripsiSandi(String deskripsiSandi) {
        this.deskripsiSandi = deskripsiSandi.toUpperCase();
    }

    public String getKodeGl() {
        return kodeGl;
    }

    public void setKodeGl(String kodeGl) {
        this.kodeGl = kodeGl.toUpperCase();
    }

    public String getDeskripsiGl() {
        return deskripsiGl;
    }

    public void setDeskripsiGl(String deskripsiGl) {
        this.deskripsiGl = deskripsiGl.toUpperCase();
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
