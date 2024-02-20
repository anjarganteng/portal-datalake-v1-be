package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class PemegangkuasaRequestDTO implements Serializable {

    private static final long serialVersionUID = -6567560593571103025L;

    private String noCif;
    private String jenisId;
    private String nomorId;
    private boolean isNew;

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif.toUpperCase();
    }

    public String getJenisId() {
        return jenisId;
    }

    public void setJenisId(String jenisId) {
        this.jenisId = jenisId.toUpperCase();
    }

    public String getNomorId() {
        return nomorId;
    }

    public void setNomorId(String nomorId) {
        this.nomorId = nomorId.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
