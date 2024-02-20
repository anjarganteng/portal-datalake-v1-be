package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PbiRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedPbi;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpbi")
public class Pbi {

    @EmbeddedId
    private EmbedPbi embedPbi;

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

    @Column(name = "suku_bunga")
    private BigDecimal sukuBunga;

    public Pbi() {
    }

    public Pbi(EmbedPbi embedPbi, PbiRequestDTO pbiRequestDTO) {
        this.embedPbi = embedPbi;
        deskripsiCoa = pbiRequestDTO.getDeskripsiCoa();
        dalamLuarNegeri = pbiRequestDTO.getDalamLuarNegeri();
        jenis = pbiRequestDTO.getJenis();
        sandiJenis = pbiRequestDTO.getSandiJenis();
        branch = pbiRequestDTO.getBranch();
        wilayah = pbiRequestDTO.getWilayah();
        hubDgnBank = pbiRequestDTO.getHubDgnBank();
        sandiBank = pbiRequestDTO.getSandiBank();
        noRekening = pbiRequestDTO.getNoRekening();
        jatuhTempo = pbiRequestDTO.getJatuhTempo();
        klasifikasiAset = pbiRequestDTO.getKlasifikasiAset();
        kualitasAset = pbiRequestDTO.getKualitasAset();
        sukuBunga = pbiRequestDTO.getSukuBunga();
    }

    public EmbedPbi getEmbedPbi() {
        return embedPbi;
    }

    public void setEmbedPbi(EmbedPbi embedPbi) {
        this.embedPbi = embedPbi;
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

    public BigDecimal getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(BigDecimal sukuBunga) {
        this.sukuBunga = sukuBunga;
    }
}
