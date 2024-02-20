package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class GolonganPemilikIndividuRequestDTO implements Serializable {

    private static final long serialVersionUID = -2261122248186297134L;

    public String tipeGolPemilik;

    public String getTipeGolPemilik() {
        return tipeGolPemilik;
    }

    public void setTipeGolPemilik(String tipeGolPemilik) {
        this.tipeGolPemilik = tipeGolPemilik.toUpperCase();
    }
}
