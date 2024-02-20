package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedCifToFi;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_cif_to_fi_bot")
public class CifToFi {

    @EmbeddedId
    protected EmbedCifToFi embedCifToFi;

    public CifToFi() {
    }

    public CifToFi(EmbedCifToFi tabelCifToFiPK) {
        this.embedCifToFi = tabelCifToFiPK;
    }

    public CifToFi(String cifNo, String flag) {
        this.embedCifToFi = new EmbedCifToFi(cifNo, flag);
    }

    public EmbedCifToFi getEmbedCifToFi() {
        return embedCifToFi;
    }

    public void setEmbedCifToFi(EmbedCifToFi embedCifToFi) {
        this.embedCifToFi = embedCifToFi;
    }

}
