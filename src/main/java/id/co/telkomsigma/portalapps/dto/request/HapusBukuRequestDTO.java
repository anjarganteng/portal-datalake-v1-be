package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class HapusBukuRequestDTO implements Serializable {

    private static final long serialVersionUID = -8814920132275308938L;

    private String sandiAntasena;
    private String deskripsiSandi;
    private String rupiah;
    private String valas;
    private boolean isNew;

    public String getSandiAntasena() {
        return sandiAntasena;
    }

    public void setSandiAntasena(String sandiAntasena) {
        this.sandiAntasena = sandiAntasena.toUpperCase();
    }

    public String getDeskripsiSandi() {
        return deskripsiSandi;
    }

    public void setDeskripsiSandi(String deskripsiSandi) {
        this.deskripsiSandi = deskripsiSandi.toUpperCase();
    }

    public String getRupiah() {
        return rupiah;
    }

    public void setRupiah(String rupiah) {
        this.rupiah = rupiah.toUpperCase();
    }

    public String getValas() {
        return valas;
    }

    public void setValas(String valas) {
        this.valas = valas.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
