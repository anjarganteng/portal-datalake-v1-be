package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ArusKasKbu;

import java.sql.Date;

public class ArusKasKbuResponseDTO {

    private static final long serialVersionUID = 7110801614777583808L;

    private String uniqueId;
    private Date tanggalProyeksi;
    private String sandiReferensi;
    private String jenisArusKas;
    private String jenisValuta;
    private String nominalProyeksi;

    public ArusKasKbuResponseDTO() {
    }

    public ArusKasKbuResponseDTO(ArusKasKbu arusKasKbu) {
        uniqueId = arusKasKbu.getUniqueId();
        tanggalProyeksi = arusKasKbu.getTanggalProyeksi();
        sandiReferensi = arusKasKbu.getSandiReferensi();
        jenisArusKas = arusKasKbu.getJenisArusKas();
        jenisValuta = arusKasKbu.getJenisValuta();
        nominalProyeksi = arusKasKbu.getNominalProyeksi();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Date getTanggalProyeksi() {
        return tanggalProyeksi;
    }

    public void setTanggalProyeksi(Date tanggalProyeksi) {
        this.tanggalProyeksi = tanggalProyeksi;
    }

    public String getSandiReferensi() {
        return sandiReferensi;
    }

    public void setSandiReferensi(String sandiReferensi) {
        this.sandiReferensi = sandiReferensi;
    }

    public String getJenisArusKas() {
        return jenisArusKas;
    }

    public void setJenisArusKas(String jenisArusKas) {
        this.jenisArusKas = jenisArusKas;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }

    public String getNominalProyeksi() {
        return nominalProyeksi;
    }

    public void setNominalProyeksi(String nominalProyeksi) {
        this.nominalProyeksi = nominalProyeksi;
    }
}
