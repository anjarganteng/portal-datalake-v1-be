package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.KodeTransaksiKasValas;

/**
 * @author satiya
 */
public class KodeTransaksiKasValasResponseDTO {

    private static final long serialVersionUID = -5566976192080086991L;

    private String kodeTransaksi;
    private String deskripsi;
    private String flagDebet;
    private String flagKredit;

    public KodeTransaksiKasValasResponseDTO() {
    }

    public KodeTransaksiKasValasResponseDTO(KodeTransaksiKasValas kodeTransaksiKasValas) {
        kodeTransaksi = kodeTransaksiKasValas.getKodeTransaksi();
        deskripsi = kodeTransaksiKasValas.getDeskripsi();
        flagDebet = kodeTransaksiKasValas.getFlagDebet();
        flagKredit = kodeTransaksiKasValas.getFlagKredit();
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFlagDebet() {
        return flagDebet;
    }

    public void setFlagDebet(String flagDebet) {
        this.flagDebet = flagDebet;
    }

    public String getFlagKredit() {
        return flagKredit;
    }

    public void setFlagKredit(String flagKredit) {
        this.flagKredit = flagKredit;
    }
}
