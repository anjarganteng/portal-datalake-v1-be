package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PemegangkuasaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpemegangkuasa")
public class Pemegangkuasa {

    @Id
    @Column(name = "NO_CIF")
    private String noCif;

    @Column(name = "JENIS_ID")
    private String jenisId;

    @Column(name = "NOMOR_ID")
    private String nomorId;

    public Pemegangkuasa() {
    }

    public Pemegangkuasa(PemegangkuasaRequestDTO pemegangkuasaRequestDTO) {
        noCif = pemegangkuasaRequestDTO.getNoCif();
        jenisId = pemegangkuasaRequestDTO.getJenisId();
        nomorId = pemegangkuasaRequestDTO.getNomorId();
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }

    public String getJenisId() {
        return jenisId;
    }

    public void setJenisId(String jenisId) {
        this.jenisId = jenisId;
    }

    public String getNomorId() {
        return nomorId;
    }

    public void setNomorId(String nomorId) {
        this.nomorId = nomorId;
    }
}
