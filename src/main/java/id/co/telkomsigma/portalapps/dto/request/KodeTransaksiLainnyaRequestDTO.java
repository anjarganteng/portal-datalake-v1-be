package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class KodeTransaksiLainnyaRequestDTO implements Serializable {

    private static final long serialVersionUID = -640912046827180896L;

    public String kodeTransaksi;

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi.toUpperCase();
    }
}
