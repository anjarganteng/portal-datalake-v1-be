package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.PengecualianKodeAgunan;

/**
 * @author satiya
 */
public class PengecualianKodeAgunanResponseDTO {

    private static final long serialVersionUID = 4696145639717298063L;

    private String kodeAgunanCore;

    public PengecualianKodeAgunanResponseDTO() {
    }

    public PengecualianKodeAgunanResponseDTO(PengecualianKodeAgunan pengecualianKodeAgunan) {
        kodeAgunanCore = pengecualianKodeAgunan.getKodeAgunanCore();
    }

    public String getKodeAgunanCore() {
        return kodeAgunanCore;
    }

    public void setKodeAgunanCore(String kodeAgunanCore) {
        this.kodeAgunanCore = kodeAgunanCore;
    }
}
