package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.HapusBuku;

/**
 * @author satiya
 */
public class HapusBukuResponseDTO {

    private static final long serialVersionUID = -2781677132806907156L;

    private String sandiAntasena;
    private String deskripsiSandi;
    private String rupiah;
    private String valas;

    public HapusBukuResponseDTO() {
    }

    public HapusBukuResponseDTO(HapusBuku hapusBuku) {
        sandiAntasena = hapusBuku.getSandiAntasena();
        deskripsiSandi = hapusBuku.getDeskripsiSandi();
        rupiah = hapusBuku.getRupiah();
        valas = hapusBuku.getValas();
    }

    public String getSandiAntasena() {
        return sandiAntasena;
    }

    public void setSandiAntasena(String sandiAntasena) {
        this.sandiAntasena = sandiAntasena;
    }

    public String getDeskripsiSandi() {
        return deskripsiSandi;
    }

    public void setDeskripsiSandi(String deskripsiSandi) {
        this.deskripsiSandi = deskripsiSandi;
    }

    public String getRupiah() {
        return rupiah;
    }

    public void setRupiah(String rupiah) {
        this.rupiah = rupiah;
    }

    public String getValas() {
        return valas;
    }

    public void setValas(String valas) {
        this.valas = valas;
    }
}
