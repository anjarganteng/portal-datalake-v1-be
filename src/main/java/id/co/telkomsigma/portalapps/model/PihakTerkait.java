package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PihakTerkaitRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpts")
public class PihakTerkait {

    @Id
    @Column(name = "sandi_antasena")
    private String sandiAntasena;

    @Column(name = "deskripsi_sandi")
    private String deskripsiSandi;

    @Column(name = "kode_gl")
    private String kodeGl;

    @Column(name = "deskripsi_gl")
    private String deskripsiGl;

    @Column(name = "mata_uang")
    private String mataUang;

    public PihakTerkait() {
    }

    public PihakTerkait(PihakTerkaitRequestDTO pihakTerkaitRequestDTO) {
        sandiAntasena = pihakTerkaitRequestDTO.getSandiAntasena();
        deskripsiSandi = pihakTerkaitRequestDTO.getDeskripsiSandi();
        kodeGl = pihakTerkaitRequestDTO.getKodeGl();
        deskripsiGl = pihakTerkaitRequestDTO.getDeskripsiGl();
        mataUang = pihakTerkaitRequestDTO.getMataUang();
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
