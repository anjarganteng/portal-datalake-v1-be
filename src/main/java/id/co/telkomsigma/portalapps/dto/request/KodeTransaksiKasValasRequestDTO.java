package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class KodeTransaksiKasValasRequestDTO implements Serializable {

    private static final long serialVersionUID = 6614317331104622619L;

    private String kodeTransaksi;
    private String deskripsi;
    private String flagDebet;
    private String flagKredit;
    private boolean isNew;

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi.toUpperCase();
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi.toUpperCase();
    }

    public String getFlagDebet() {
        return flagDebet;
    }

    public void setFlagDebet(String flagDebet) {
        this.flagDebet = flagDebet.toUpperCase();
    }

    public String getFlagKredit() {
        return flagKredit;
    }

    public void setFlagKredit(String flagKredit) {
        this.flagKredit = flagKredit.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
