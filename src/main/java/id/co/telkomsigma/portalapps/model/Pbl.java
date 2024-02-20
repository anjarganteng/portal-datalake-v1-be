package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PblRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedPbl;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpbl")
public class Pbl {

    @EmbeddedId
    private EmbedPbl embedPbl;

    @Column(name = "deskripsi_coa")
    private String deskripsiCoa;

    @Column(name = "dalam_luar_negeri")
    private String dalamLuarNegeri;

    @Column(name = "jenis")
    private String jenis;

    @Column(name = "sandi_jenis")
    private String sandiJenis;

    @Column(name = "branch")
    private String branch;

    @Column(name = "wilayah")
    private String wilayah;

    @Column(name = "hub_dgn_bank")
    private String hubDgnBank;

    @Column(name = "sandi_bank")
    private String sandiBank;

    @Column(name = "no_rekening")
    private String noRekening;

    @Column(name = "jatuh_tempo")
    private String jatuhTempo;

    @Column(name = "klasifikasi_aset")
    private String klasifikasiAset;

    @Column(name = "kualitas_aset")
    private String kualitasAset;

    @Column(name = "pd")
    private BigDecimal pd;

    @Column(name = "lgd")
    private BigDecimal lgd;

    @Column(name = "suku_bunga")
    private BigDecimal sukuBunga;

    @Column(name = "jns_suku_bunga")
    private String jnsSukuBunga;

    public Pbl() {
    }

    public Pbl(EmbedPbl embedPbl, PblRequestDTO pblRequestDTO) {
        this.embedPbl = embedPbl;
        deskripsiCoa = pblRequestDTO.getDeskripsiCoa();
        dalamLuarNegeri = pblRequestDTO.getDalamLuarNegeri();
        jenis = pblRequestDTO.getJenis();
        sandiJenis = pblRequestDTO.getSandiJenis();
        branch = pblRequestDTO.getBranch();
        wilayah = pblRequestDTO.getWilayah();
        hubDgnBank = pblRequestDTO.getHubDgnBank();
        sandiBank = pblRequestDTO.getSandiBank();
        noRekening = pblRequestDTO.getNoRekening();
        jatuhTempo = pblRequestDTO.getJatuhTempo();
        klasifikasiAset = pblRequestDTO.getKlasifikasiAset();
        kualitasAset = pblRequestDTO.getKualitasAset();
        pd = pblRequestDTO.getPd();
        lgd = pblRequestDTO.getLgd();
        sukuBunga = pblRequestDTO.getSukuBunga();
        jnsSukuBunga = pblRequestDTO.getJnsSukuBunga();
    }

    public EmbedPbl getEmbedPbl() {
        return embedPbl;
    }

    public void setEmbedPbl(EmbedPbl embedPbl) {
        this.embedPbl = embedPbl;
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa;
    }

    public String getDalamLuarNegeri() {
        return dalamLuarNegeri;
    }

    public void setDalamLuarNegeri(String dalamLuarNegeri) {
        this.dalamLuarNegeri = dalamLuarNegeri;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getSandiJenis() {
        return sandiJenis;
    }

    public void setSandiJenis(String sandiJenis) {
        this.sandiJenis = sandiJenis;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getHubDgnBank() {
        return hubDgnBank;
    }

    public void setHubDgnBank(String hubDgnBank) {
        this.hubDgnBank = hubDgnBank;
    }

    public String getSandiBank() {
        return sandiBank;
    }

    public void setSandiBank(String sandiBank) {
        this.sandiBank = sandiBank;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(String jatuhTempo) {
        this.jatuhTempo = jatuhTempo;
    }

    public String getKlasifikasiAset() {
        return klasifikasiAset;
    }

    public void setKlasifikasiAset(String klasifikasiAset) {
        this.klasifikasiAset = klasifikasiAset;
    }

    public String getKualitasAset() {
        return kualitasAset;
    }

    public void setKualitasAset(String kualitasAset) {
        this.kualitasAset = kualitasAset;
    }

    public BigDecimal getPd() {
        return pd;
    }

    public void setPd(BigDecimal pd) {
        this.pd = pd;
    }

    public BigDecimal getLgd() {
        return lgd;
    }

    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }

    public BigDecimal getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(BigDecimal sukuBunga) {
        this.sukuBunga = sukuBunga;
    }

    public String getJnsSukuBunga() {
        return jnsSukuBunga;
    }

    public void setJnsSukuBunga(String jnsSukuBunga) {
        this.jnsSukuBunga = jnsSukuBunga;
    }
}
