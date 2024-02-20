package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class RekeningSlikRequestDTO implements Serializable {

    private static final long serialVersionUID = -2227042541193813947L;

    private String noValid;
    private String noSlik;
    private boolean isNew;

    public String getNoValid() {
        return noValid;
    }

    public void setNoValid(String noValid) {
        this.noValid = noValid;
    }

    public String getNoSlik() {
        return noSlik;
    }

    public void setNoSlik(String noSlik) {
        this.noSlik = noSlik;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
