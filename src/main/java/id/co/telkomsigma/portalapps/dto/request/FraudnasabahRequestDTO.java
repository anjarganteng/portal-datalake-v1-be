package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class FraudnasabahRequestDTO implements Serializable {

    private static final long serialVersionUID = -2593785557323609911L;

    private String noCif;

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif.toUpperCase();
    }
}
