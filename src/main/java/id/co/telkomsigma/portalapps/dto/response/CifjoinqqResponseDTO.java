package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Cifjoinqq;

/**
 * @author satiya
 */
public class CifjoinqqResponseDTO {

    private static final long serialVersionUID = 1742909945199320569L;

    private String cifQq;
    private String namaLengkap;

    public CifjoinqqResponseDTO() {
    }

    public CifjoinqqResponseDTO(Cifjoinqq cifjoinqq) {
        cifQq = cifjoinqq.getCifQq();
        namaLengkap = cifjoinqq.getNamaLengkap();
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
