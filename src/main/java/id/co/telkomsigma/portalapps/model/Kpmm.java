package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedKpmm;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_kpmm_bot")
public class Kpmm {

    @EmbeddedId
    protected EmbedKpmm embedKpmm;
    
    @Column(name = "nama_komponen")
    private String namaKomponen;
    
    @Column(name = "jumlah")
    private BigDecimal jumlah;

    public Kpmm() {
    }

    public Kpmm(EmbedKpmm embedKpmm) {
        this.embedKpmm = embedKpmm;
    }

    public Kpmm(String kodeForm, String kodeKomponen) {
        this.embedKpmm = new EmbedKpmm(kodeForm, kodeKomponen);
    }

    public EmbedKpmm getEmbedKpmm() {
        return embedKpmm;
    }

    public void setEmbedKpmm(EmbedKpmm embedKpmm) {
        this.embedKpmm = embedKpmm;
    }

    public String getNamaKomponen() {
        return namaKomponen;
    }

    public void setNamaKomponen(String namaKomponen) {
        this.namaKomponen = namaKomponen;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

}
