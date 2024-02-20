package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.SlikAgunanLamaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedSlikAgunanLama implements Serializable {

    @Column(name = "no_rek_lama")
    private String noSlikLama;

    @Column(name = "no_agunan_lama")
    private String noAgunanLama;

    public EmbedSlikAgunanLama() {
    }

    public EmbedSlikAgunanLama(SlikAgunanLamaRequestDTO reqEmbedDTO) {
        noSlikLama = reqEmbedDTO.getNoSlikLama();
        noAgunanLama = reqEmbedDTO.getNoAgunanLama();
    }

    public String getNoSlikLama() {
        return noSlikLama;
    }

    public void setNoSlikLama(String noSlikLama) {
        this.noSlikLama = noSlikLama;
    }

    public String getNoAgunanLama() {
        return noAgunanLama;
    }

    public void setNoAgunanLama(String noAgunanLama) {
        this.noAgunanLama = noAgunanLama;
    }
}
