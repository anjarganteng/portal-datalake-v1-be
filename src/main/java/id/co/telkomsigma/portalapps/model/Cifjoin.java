package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CifjoinRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedCifjoin;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcifjoin")
public class Cifjoin {

    @Column(name = "FLAG_NASABAH")
    private String flagNasabah;

    @Column(name = "TIPE_NASABAH")
    private String tipeNasabah;

    @Column(name = "NO_IDENTITAS")
    private String noIdentitas;

    @EmbeddedId
    private EmbedCifjoin embedCifjoin;

    @Column(name = "TIPE_JOIN")
    private String tipeJoin;

    public Cifjoin() {
    }

    public Cifjoin(EmbedCifjoin embedCifjoin, CifjoinRequestDTO cifjoinRequestDTO) {
        flagNasabah = cifjoinRequestDTO.getFlagNasabah();
        tipeNasabah = cifjoinRequestDTO.getTipeNasabah();
        noIdentitas = cifjoinRequestDTO.getNoIdentitas();
        this.embedCifjoin = embedCifjoin;
        tipeJoin = cifjoinRequestDTO.getTipeJoin();
    }

    public String getFlagNasabah() {
        return flagNasabah;
    }

    public void setFlagNasabah(String flagNasabah) {
        this.flagNasabah = flagNasabah;
    }

    public String getTipeNasabah() {
        return tipeNasabah;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public EmbedCifjoin getEmbedCifjoin() {
        return embedCifjoin;
    }

    public void setEmbedCifjoin(EmbedCifjoin embedCifjoin) {
        this.embedCifjoin = embedCifjoin;
    }

    public String getTipeJoin() {
        return tipeJoin;
    }

    public void setTipeJoin(String tipeJoin) {
        this.tipeJoin = tipeJoin;
    }
}
