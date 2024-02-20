package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Pts;

/**
 * @author satiya
 */
public class PtsResponseDTO {

    private static final long serialVersionUID = 7960957301608467734L;

    private String sandiAntasena;
    private String deskripsiSandi;
    private String kodeGl;
    private String deskripsiGl;
    private String mataUang;

    public PtsResponseDTO() {
    }

    public PtsResponseDTO(Pts pts) {
        sandiAntasena = pts.getSandiAntasena();
        deskripsiSandi = pts.getDeskripsiSandi();
        kodeGl = pts.getKodeGl();
        deskripsiGl = pts.getDeskripsiGl();
        mataUang = pts.getMataUang();
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

    public String getKodeGl() {
        return kodeGl;
    }

    public void setKodeGl(String kodeGl) {
        this.kodeGl = kodeGl;
    }

    public String getDeskripsiGl() {
        return deskripsiGl;
    }

    public void setDeskripsiGl(String deskripsiGl) {
        this.deskripsiGl = deskripsiGl;
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }
}
