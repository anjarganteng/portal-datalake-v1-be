package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class PengecualianKodeAgunanRequestDTO implements Serializable {

    private static final long serialVersionUID = -9105178344041080939L;

    private String kodeAgunanCore;

    public String getKodeAgunanCore() {
        return kodeAgunanCore;
    }

    public void setKodeAgunanCore(String kodeAgunanCore) {
        this.kodeAgunanCore = kodeAgunanCore.toUpperCase();
    }
}
