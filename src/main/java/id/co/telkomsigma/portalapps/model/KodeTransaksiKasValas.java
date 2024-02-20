package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.KodeTransaksiKasValasRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelkodetransaksikasvalas")
public class KodeTransaksiKasValas {

    @Id
    @Column(name = "kode_transaksi")
    private String kodeTransaksi;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "flag_debet")
    private String flagDebet;

    @Column(name = "flag_kredit")
    private String flagKredit;

    public KodeTransaksiKasValas() {
    }

    public KodeTransaksiKasValas(KodeTransaksiKasValasRequestDTO kodeTransaksiKasValasRequestDTO) {
        kodeTransaksi = kodeTransaksiKasValasRequestDTO.getKodeTransaksi();
        deskripsi = kodeTransaksiKasValasRequestDTO.getDeskripsi();
        flagDebet = kodeTransaksiKasValasRequestDTO.getFlagDebet();
        flagKredit = kodeTransaksiKasValasRequestDTO.getFlagKredit();
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

