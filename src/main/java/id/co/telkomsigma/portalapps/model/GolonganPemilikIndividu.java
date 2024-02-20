package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.GolonganPemilikIndividuRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelgolpemilikindividu")
public class GolonganPemilikIndividu {

    @Id
    @Column(name = "tipe_gol_pemilik")
    private String tipeGolPemilik;

    public GolonganPemilikIndividu() {
    }

    public GolonganPemilikIndividu(GolonganPemilikIndividuRequestDTO golonganPemilikIndividuRequestDTO) {
        tipeGolPemilik = golonganPemilikIndividuRequestDTO.getTipeGolPemilik();
    }

    public String getTipeGolPemilik() {
        return tipeGolPemilik;
    }

    public void setTipeGolPemilik(String tipeGolPemilik) {
        this.tipeGolPemilik = tipeGolPemilik;
    }
}
