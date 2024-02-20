package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.SlikAgunanLama;

/**
 * @author satiya
 */
public class SlikAgunanLamaResponseDTO {

    private static final long serialVersionUID = -7267549040520113541L;

    private String noSlikLama;
    private String noAgunanLama;

    public SlikAgunanLamaResponseDTO() {
    }

    public SlikAgunanLamaResponseDTO(SlikAgunanLama slikAgunanLama) {
        noSlikLama = slikAgunanLama.getEmbedSlikAgunanLama().getNoSlikLama();
        noAgunanLama = slikAgunanLama.getEmbedSlikAgunanLama().getNoAgunanLama();
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
