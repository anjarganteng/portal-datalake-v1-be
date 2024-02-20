package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.NoAgunanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedNoAgunan implements Serializable {

    @Column(name = "no_agunan_baru")
    private String noAgunanBaru;

    @Column(name = "no_agunan_lama")
    private String noAgunanLama;

    public EmbedNoAgunan() {
    }

    public EmbedNoAgunan(NoAgunanRequestDTO reqEmbedDTO) {
        noAgunanBaru = reqEmbedDTO.getNoAgunanBaru();
        noAgunanLama = reqEmbedDTO.getNoAgunanLama();

    }

    public String getNoAgunanBaru() {
        return noAgunanBaru;
    }

    public void setNoAgunanBaru(String noAgunanBaru) {
        this.noAgunanBaru = noAgunanBaru;
    }

    public String getNoAgunanLama() {
        return noAgunanLama;
    }

    public void setNoAgunanLama(String noAgunanLama) {
        this.noAgunanLama = noAgunanLama;
    }
}
