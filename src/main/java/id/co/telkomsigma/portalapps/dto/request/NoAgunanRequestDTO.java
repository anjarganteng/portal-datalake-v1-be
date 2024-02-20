package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class NoAgunanRequestDTO implements Serializable {

    private static final long serialVersionUID = -7941874048087930824L;

    private String noAgunanBaru;
    private String noAgunanLama;
    private boolean isNew;

    public String getNoAgunanBaru() {
        return noAgunanBaru;
    }

    public void setNoAgunanBaru(String noAgunanBaru) {
        this.noAgunanBaru = noAgunanBaru.toUpperCase();
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
