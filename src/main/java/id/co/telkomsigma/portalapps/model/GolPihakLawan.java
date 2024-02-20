package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.GolPihakLawanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelgolpihaklawan")
public class GolPihakLawan {

    @Id
    @Column(name = "golongan_pihak_lawan")
    private String golonganPihakLawan;

    @Column(name = "gol_debitur_kreditur")
    private String golDebiturKreditur;

    public GolPihakLawan() {
    }

    public GolPihakLawan(GolPihakLawanRequestDTO golPihakLawanRequestDTO) {
        golonganPihakLawan = golPihakLawanRequestDTO.getGolonganPihakLawan();
        golDebiturKreditur = golPihakLawanRequestDTO.getGolDebiturKreditur();
    }

    public String getGolonganPihakLawan() {
        return golonganPihakLawan;
    }

    public void setGolonganPihakLawan(String golonganPihakLawan) {
        this.golonganPihakLawan = golonganPihakLawan;
    }

    public String getGolDebiturKreditur() {
        return golDebiturKreditur;
    }

    public void setGolDebiturKreditur(String golDebiturKreditur) {
        this.golDebiturKreditur = golDebiturKreditur;
    }
}
