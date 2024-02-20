package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.PihakLawanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedPihakLawan implements Serializable {

    @Column(name = "KD_CABANG")
    private String kdCabang;

    @Column(name = "ID_PIHAK_LAWAN")
    private String idPihakLawan;

    public EmbedPihakLawan() {
    }

    public EmbedPihakLawan(PihakLawanRequestDTO pihakLawanRequestDTO) {
        kdCabang = pihakLawanRequestDTO.getKdCabang();
        idPihakLawan = pihakLawanRequestDTO.getIdPihakLawan();
    }

    public String getKdCabang() {
        return kdCabang;
    }

    public void setKdCabang(String kdCabang) {
        this.kdCabang = kdCabang;
    }

    public String getIdPihakLawan() {
        return idPihakLawan;
    }

    public void setIdPihakLawan(String idPihakLawan) {
        this.idPihakLawan = idPihakLawan;
    }
}
