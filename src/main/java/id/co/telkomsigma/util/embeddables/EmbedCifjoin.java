package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.CifjoinRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmbedCifjoin implements Serializable {

    @Column(name = "CIF")
    private String cif;

    @Column(name = "CIF_JOIN")
    private String cifJoin;

    public EmbedCifjoin() {
    }

    public EmbedCifjoin(CifjoinRequestDTO cifjoinRequestDTO) {
        cif = cifjoinRequestDTO.getCif();
        cifJoin = cifjoinRequestDTO.getCifJoin();
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCifJoin() {
        return cifJoin;
    }

    public void setCifJoin(String cifJoin) {
        this.cifJoin = cifJoin;
    }
}
