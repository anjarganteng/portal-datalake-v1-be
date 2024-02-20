package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PtsRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpts")
public class Pts {

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

    public Pts() {
    }

    public Pts(PtsRequestDTO ptsRequestDTO) {
        sandiAntasena = ptsRequestDTO.getSandiAntasena();
        deskripsiSandi = ptsRequestDTO.getDeskripsiSandi();
        kodeGl = ptsRequestDTO.getKodeGl();
        deskripsiGl = ptsRequestDTO.getDeskripsiGl();
        mataUang = ptsRequestDTO.getMataUang();
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
