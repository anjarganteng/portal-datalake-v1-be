package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CoaRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedCoa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcoa")
public class Coa {

    @EmbeddedId
    private EmbedCoa embedCoa;

    public Coa() {
    }

    public Coa(EmbedCoa embedCoa, CoaRequestDTO coaRequestDTO) {
        this.embedCoa = embedCoa;
    }

    public EmbedCoa getEmbedCoa() {
        return embedCoa;
    }

    public void setEmbedCoa(EmbedCoa embedCoa) {
        this.embedCoa = embedCoa;
    }
}
