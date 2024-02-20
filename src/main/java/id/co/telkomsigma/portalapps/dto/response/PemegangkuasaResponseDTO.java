package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Pemegangkuasa;

/**
 * @author satiya
 */
public class PemegangkuasaResponseDTO {

    private static final long serialVersionUID = -8341812248018840081L;

    private String noCif;
    private String jenisId;
    private String nomorId;

    public PemegangkuasaResponseDTO() {
    }

    public PemegangkuasaResponseDTO(Pemegangkuasa pemegangkuasa) {
        noCif = pemegangkuasa.getNoCif();
        jenisId = pemegangkuasa.getJenisId();
        nomorId = pemegangkuasa.getNomorId();
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }

    public String getJenisId() {
        return jenisId;
    }

    public void setJenisId(String jenisId) {
        this.jenisId = jenisId;
    }

    public String getNomorId() {
        return nomorId;
    }

    public void setNomorId(String nomorId) {
        this.nomorId = nomorId;
    }
}
