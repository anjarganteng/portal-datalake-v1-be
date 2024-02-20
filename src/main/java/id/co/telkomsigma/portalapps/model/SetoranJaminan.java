package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.SetoranJaminanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelsjn")
public class SetoranJaminan {

    @Id
    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "deskripsi_coa")
    private String deskripsiCoa;

    @Column(name = "tujuan_setoran_jaminan")
    private String tujuanSetoranJaminan;

    @Column(name = "golongan_pemilik")
    private String golonganPemilik;

    @Column(name = "hub_dgn_pelapor")
    private String hubDgnPelapor;

    @Column(name = "pend_bkn_pend")
    private String pendBknPend;

    public SetoranJaminan() {
    }

    public SetoranJaminan(SetoranJaminanRequestDTO setoranJaminanRequestDTO) {
        kodeCoa = setoranJaminanRequestDTO.getKodeCoa();
        deskripsiCoa = setoranJaminanRequestDTO.getDeskripsiCoa();
        tujuanSetoranJaminan = setoranJaminanRequestDTO.getTujuanSetoranJaminan();
        golonganPemilik = setoranJaminanRequestDTO.getGolonganPemilik();
        hubDgnPelapor = setoranJaminanRequestDTO.getHubDgnPelapor();
        pendBknPend = setoranJaminanRequestDTO.getPendBknPend();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa;
    }

    public String getTujuanSetoranJaminan() {
        return tujuanSetoranJaminan;
    }

    public void setTujuanSetoranJaminan(String tujuanSetoranJaminan) {
        this.tujuanSetoranJaminan = tujuanSetoranJaminan;
    }

    public String getGolonganPemilik() {
        return golonganPemilik;
    }

    public void setGolonganPemilik(String golonganPemilik) {
        this.golonganPemilik = golonganPemilik;
    }

    public String getHubDgnPelapor() {
        return hubDgnPelapor;
    }

    public void setHubDgnPelapor(String hubDgnPelapor) {
        this.hubDgnPelapor = hubDgnPelapor;
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend;
    }
}
