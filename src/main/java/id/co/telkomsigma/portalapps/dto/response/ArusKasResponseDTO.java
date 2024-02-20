package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ArusKas;

import java.sql.Date;

public class ArusKasResponseDTO {

    private static final long serialVersionUID = -1056229741097329872L;

    private String uniqueId;
    private Date tanggalProyeksi;
    private String sandiReferensi;
    private String jenisArusKas;
    private String jenisValuta;
    private String nominalProyeksi;

    public ArusKasResponseDTO() {
    }

    public ArusKasResponseDTO(ArusKas arusKas) {
        uniqueId = arusKas.getUniqueId();
        tanggalProyeksi = arusKas.getTanggalProyeksi();
        sandiReferensi = arusKas.getSandiReferensi();
        jenisArusKas = arusKas.getJenisArusKas();
        jenisValuta = arusKas.getJenisValuta();
        nominalProyeksi = arusKas.getNominalProyeksi();
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
