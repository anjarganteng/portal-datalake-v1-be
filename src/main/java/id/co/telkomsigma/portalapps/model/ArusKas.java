package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.ArusKasRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabel_proyeksi_arus_kas_rpp")
public class ArusKas {

    @Id
    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "tanggal_proyeksi")
    private Date tanggalProyeksi;

    @Column(name = "sandi_referensi")
    private String sandiReferensi;

    @Column(name = "jenis_arus_kas")
    private String jenisArusKas;

    @Column(name = "jenis_valuta")
    private String jenisValuta;

    @Column(name = "nominal_proyeksi")
    private String nominalProyeksi;

    public ArusKas() {
    }

    public ArusKas(ArusKasRequestDTO arusKasRequestDTO) {
        uniqueId = arusKasRequestDTO.getUniqueId();
        tanggalProyeksi = arusKasRequestDTO.getTanggalProyeksi();
        sandiReferensi = arusKasRequestDTO.getSandiReferensi();
        jenisArusKas = arusKasRequestDTO.getJenisArusKas();
        jenisValuta = arusKasRequestDTO.getJenisValuta();
        nominalProyeksi = arusKasRequestDTO.getNominalProyeksi();
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
