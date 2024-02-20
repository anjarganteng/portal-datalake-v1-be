package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class MappingNcdRequestDTO implements Serializable {

    private static final long serialVersionUID = -6236309838251628877L;

    private String noDeposito;
    private String noCif;
    private boolean isNew;

    public String getNoDeposito() {
        return noDeposito;
    }

    public void setNoDeposito(String noDeposito) {
        this.noDeposito = noDeposito.toUpperCase();
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
