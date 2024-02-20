package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.AtmRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelatm")
public class Atm {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "jenis")
    private String jenis;

    @Column(name = "status")
    private String status;

    @Column(name = "lokasi")
    private String lokasi;

    @Column(name = "usaha")
    private String usaha;

    @Column(name = "keterangan")
    private String keterangan;

    public Atm() {
    }

    public Atm(AtmRequestDTO atmRequestDTO) {
        id = atmRequestDTO.getId();
        jenis = atmRequestDTO.getJenis();
        status = atmRequestDTO.getStatus();
        lokasi = atmRequestDTO.getLokasi();
        usaha = atmRequestDTO.getUsaha();
        keterangan = atmRequestDTO.getKeterangan();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getUsaha() {
        return usaha;
    }

    public void setUsaha(String usaha) {
        this.usaha = usaha;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
