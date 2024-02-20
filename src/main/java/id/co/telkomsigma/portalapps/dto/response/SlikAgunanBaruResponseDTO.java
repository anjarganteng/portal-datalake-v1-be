package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.SlikAgunanBaru;

/**
 * @author satiya
 */
public class SlikAgunanBaruResponseDTO {

    private static final long serialVersionUID = -7267549040520113541L;

    private String noValid;
    private String noAgunanBaru;

    public SlikAgunanBaruResponseDTO() {
    }

    public SlikAgunanBaruResponseDTO(SlikAgunanBaru slikAgunanBaru) {
        noValid = slikAgunanBaru.getEmbedSlikAgunanBaru().getNoValid();
        noAgunanBaru = slikAgunanBaru.getEmbedSlikAgunanBaru().getNoAgunanBaru();
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
