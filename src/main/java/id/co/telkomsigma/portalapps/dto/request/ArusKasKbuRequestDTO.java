package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.sql.Date;

public class ArusKasKbuRequestDTO implements Serializable {

    private static final long serialVersionUID = -5317662156629955776L;

    private String uniqueId;
    private Date tanggalProyeksi;
    private String sandiReferensi;
    private String jenisArusKas;
    private String jenisValuta;
    private String nominalProyeksi;
    private boolean isNew;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId.toUpperCase();
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
        this.sandiReferensi = sandiReferensi.toUpperCase();
    }

    public String getJenisArusKas() {
        return jenisArusKas;
    }

    public void setJenisArusKas(String jenisArusKas) {
        this.jenisArusKas = jenisArusKas.toUpperCase();
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta.toUpperCase();
    }

    public String getNominalProyeksi() {
        return nominalProyeksi;
    }

    public void setNominalProyeksi(String nominalProyeksi) {
        this.nominalProyeksi = nominalProyeksi.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
