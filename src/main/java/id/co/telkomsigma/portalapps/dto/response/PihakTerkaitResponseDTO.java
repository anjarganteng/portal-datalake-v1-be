package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.PihakTerkait;

/**
 * @author satiya
 */
public class PihakTerkaitResponseDTO {

    private static final long serialVersionUID = 4855186476298349043L;

    private String sandiAntasena;
    private String deskripsiSandi;
    private String kodeGl;
    private String deskripsiGl;
    private String mataUang;

    public PihakTerkaitResponseDTO() {
    }

    public PihakTerkaitResponseDTO(PihakTerkait pihakTerkait) {
        sandiAntasena = pihakTerkait.getSandiAntasena();
        deskripsiSandi = pihakTerkait.getDeskripsiSandi();
        kodeGl = pihakTerkait.getKodeGl();
        deskripsiGl = pihakTerkait.getDeskripsiGl();
        mataUang = pihakTerkait.getMataUang();
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
