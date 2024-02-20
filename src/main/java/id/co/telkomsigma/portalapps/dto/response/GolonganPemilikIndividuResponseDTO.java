package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.GolonganPemilikIndividu;

/**
 * @author satiya
 */
public class GolonganPemilikIndividuResponseDTO {

    private static final long serialVersionUID = -4091716765567277836L;

    public String tipeGolPemilik;

    public GolonganPemilikIndividuResponseDTO() {
    }

    public GolonganPemilikIndividuResponseDTO(GolonganPemilikIndividu golonganPemilikIndividu) {
        tipeGolPemilik = golonganPemilikIndividu.getTipeGolPemilik();
    }

    public String getTipeGolPemilik() {
        return tipeGolPemilik;
    }

    public void setTipeGolPemilik(String tipeGolPemilik) {
        this.tipeGolPemilik = tipeGolPemilik;
    }
}
