package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.NoAgunan;

/**
 * @author satiya
 */
public class NoAgunanResponseDTO {

    private static final long serialVersionUID = 7533665238539353198L;

    private String noAgunanBaru;
    private String noAgunanLama;

    public NoAgunanResponseDTO() {
    }

    public NoAgunanResponseDTO(NoAgunan noAgunan) {
        noAgunanBaru = noAgunan.getEmbedNoAgunan().getNoAgunanBaru();
        noAgunanLama = noAgunan.getEmbedNoAgunan().getNoAgunanLama();
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
