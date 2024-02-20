package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.NoAgunanRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedNoAgunan;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelmappingnoagunanbarulama")
public class NoAgunan {

    @EmbeddedId
    private EmbedNoAgunan embedNoAgunan;

    public NoAgunan() {
    }

    public NoAgunan(EmbedNoAgunan embedNoAgunan, NoAgunanRequestDTO noAgunanRequestDTO) {
        this.embedNoAgunan = embedNoAgunan;
    }

    public EmbedNoAgunan getEmbedNoAgunan() {
        return embedNoAgunan;
    }

    public void setEmbedNoAgunan(EmbedNoAgunan embedNoAgunan) {
        this.embedNoAgunan = embedNoAgunan;
    }
}
