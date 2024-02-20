package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PpaRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedPpa;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelppa")
public class Ppa {

    @EmbeddedId
    private EmbedPpa embedPpa;

    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "jenis")
    private String jenis;

    @Column(name = "deskripsi_coa")
    private String deskripsiCoa;

    @Column(name = "tgl_jth_tempo")
    private String tglJthTempo;

    @Column(name = "metode_ukur")
    private String metodeUkur;

    @Column(name = "status_aset")
    private String statusAset;

    @Column(name = "kualitas_aset")
    private String kualitasAset;

    @Column(name = "nomor_aset")
    private String nomorAset;

    @Column(name = "harga_perolehan")
    private BigDecimal hargaPerolehan;

    @Column(name = "akumulasi_susut")
    private BigDecimal akumulasiSusut;

    @Column(name = "nilai_buku")
    private BigDecimal nilaiBuku;

    public Ppa() {
    }

    public Ppa(EmbedPpa embedPpa, PpaRequestDTO ppaRequestDTO) {
        this.embedPpa = embedPpa;
        kodeCoa = ppaRequestDTO.getKodeCoa();
        deskripsiCoa = ppaRequestDTO.getDeskripsiCoa();
        jenis = ppaRequestDTO.getJenis();
        tglJthTempo = ppaRequestDTO.getTglJthTempo();
        metodeUkur = ppaRequestDTO.getMetodeUkur();
        kualitasAset = ppaRequestDTO.getKualitasAset();
        nomorAset = ppaRequestDTO.getNomorAset();
        hargaPerolehan = ppaRequestDTO.getHargaPerolehan();
        akumulasiSusut = ppaRequestDTO.getAkumulasiSusut();
        nilaiBuku = ppaRequestDTO.getNilaiBuku();
        statusAset = ppaRequestDTO.getStatusAset();
    }

    public EmbedPpa getEmbedPpa() {
        return embedPpa;
    }

    public void setEmbedPpa(EmbedPpa embedPpa) {
        this.embedPpa = embedPpa;
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa;
    }

    public String getTglJthTempo() {
        return tglJthTempo;
    }

    public void setTglJthTempo(String tglJthTempo) {
        this.tglJthTempo = tglJthTempo;
    }

    public String getMetodeUkur() {
        return metodeUkur;
    }

    public void setMetodeUkur(String metodeUkur) {
        this.metodeUkur = metodeUkur;
    }

    public String getStatusAset() {
        return statusAset;
    }

    public void setStatusAset(String statusAset) {
        this.statusAset = statusAset;
    }

    public String getKualitasAset() {
        return kualitasAset;
    }

    public void setKualitasAset(String kualitasAset) {
        this.kualitasAset = kualitasAset;
    }

    public String getNomorAset() {
        return nomorAset;
    }

    public void setNomorAset(String nomorAset) {
        this.nomorAset = nomorAset;
    }

    public BigDecimal getHargaPerolehan() {
        return hargaPerolehan;
    }

    public void setHargaPerolehan(BigDecimal hargaPerolehan) {
        this.hargaPerolehan = hargaPerolehan;
    }

    public BigDecimal getAkumulasiSusut() {
        return akumulasiSusut;
    }

    public void setAkumulasiSusut(BigDecimal akumulasiSusut) {
        this.akumulasiSusut = akumulasiSusut;
    }

    public BigDecimal getNilaiBuku() {
        return nilaiBuku;
    }

    public void setNilaiBuku(BigDecimal nilaiBuku) {
        this.nilaiBuku = nilaiBuku;
    }
}

