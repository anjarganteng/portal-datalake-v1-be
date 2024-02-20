package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class SlikAgunanLamaRequestDTO implements Serializable {

    private static final long serialVersionUID = -444246653021673266L;

    private String noSlikLama;
    private String noAgunanLama;
    private boolean isNew;

    public String getNoSlikLama() {
        return noSlikLama;
    }

    public void setNoSlikLama(String noSlikLama) {
        this.noSlikLama = noSlikLama.toUpperCase();
    }

    public String getNoAgunanLama() {
        return noAgunanLama;
    }

    public void setNoAgunanLama(String noAgunanLama) {
        this.noAgunanLama = noAgunanLama.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
