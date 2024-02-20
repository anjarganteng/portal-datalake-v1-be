package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedSlikAgunanBaru;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelslikagunanbaru")
public class SlikAgunanBaru {

    @EmbeddedId
    private EmbedSlikAgunanBaru embedSlikAgunanBaru;

    public SlikAgunanBaru() {
    }

    public SlikAgunanBaru(EmbedSlikAgunanBaru embedSlikAgunanBaru) {
        this.embedSlikAgunanBaru = embedSlikAgunanBaru;
    }

    public EmbedSlikAgunanBaru getEmbedSlikAgunanBaru() {
        return embedSlikAgunanBaru;
    }

    public void setEmbedSlikAgunanBaru(EmbedSlikAgunanBaru embedSlikAgunanBaru) {
        this.embedSlikAgunanBaru = embedSlikAgunanBaru;
    }
}
