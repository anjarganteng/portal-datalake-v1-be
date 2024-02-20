package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedSlikAgunanLama;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelmappingfasilitasagunan")
public class SlikAgunanLama {

    @EmbeddedId
    private EmbedSlikAgunanLama embedSlikAgunanLama;

    public SlikAgunanLama() {
    }

    public SlikAgunanLama(EmbedSlikAgunanLama embedSlikAgunanLama) {
        this.embedSlikAgunanLama = embedSlikAgunanLama;
    }

    public EmbedSlikAgunanLama getEmbedSlikAgunanLama() {
        return embedSlikAgunanLama;
    }

    public void setEmbedSlikAgunanLama(EmbedSlikAgunanLama embedSlikAgunanLama) {
        this.embedSlikAgunanLama = embedSlikAgunanLama;
    }
}
