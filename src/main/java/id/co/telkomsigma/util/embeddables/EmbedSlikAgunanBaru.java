package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.SlikAgunanBaruRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedSlikAgunanBaru implements Serializable {

    @Column(name = "no_valid")
    private String noValid;

    @Column(name = "no_agunan_baru")
    private String noAgunanBaru;

    public EmbedSlikAgunanBaru() {
    }

    public EmbedSlikAgunanBaru(SlikAgunanBaruRequestDTO reqEmbedDTO) {
        noValid = reqEmbedDTO.getNoValid();
        noAgunanBaru = reqEmbedDTO.getNoAgunanBaru();
    }

    public String getNoValid() {
        return noValid;
    }

    public void setNoValid(String noValid) {
        this.noValid = noValid;
    }

    public String getNoAgunanBaru() {
        return noAgunanBaru;
    }

    public void setNoAgunanBaru(String noAgunanBaru) {
        this.noAgunanBaru = noAgunanBaru;
    }
}
