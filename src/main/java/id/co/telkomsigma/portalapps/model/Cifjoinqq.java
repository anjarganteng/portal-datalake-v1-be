package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CifjoinqqRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcifjoinqq")
public class Cifjoinqq {

    @Id
    @Column(name = "CIF_QQ")
    private String cifQq;

    @Column(name = "NM_LNKP_NSB")
    private String namaLengkap;

    public Cifjoinqq() {
    }

    public Cifjoinqq(CifjoinqqRequestDTO cifjoinqqRequestDTO) {
        cifQq = cifjoinqqRequestDTO.getCifQq();
        namaLengkap = cifjoinqqRequestDTO.getNamaLengkap();
    }

    public String getCifQq() {
        return cifQq;
    }

    public void setCifQq(String cifQq) {
        this.cifQq = cifQq;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
}
