package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Ppa;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class PpaResponseDTO {

    private static final long serialVersionUID = 8308049840838272905L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String jenis;
    private String sandiJenis;
    private String cabang;
    private String wilayah;
    private String mataUang;
    private String tglMulai;
    private String tglJthTempo;
    private String metodeUkur;
    private String statusAset;
    private String kualitasAset;
    private String nomorAset;
    private BigDecimal hargaPerolehan;
    private BigDecimal akumulasiSusut;
    private BigDecimal nilaiBuku;

    public PpaResponseDTO(Ppa ppa) {
        jenis = ppa.getJenis();
        tglMulai = ppa.getEmbedPpa().getTglMulai();
        kodeCoa = ppa.getKodeCoa();
        deskripsiCoa = ppa.getDeskripsiCoa();
        sandiJenis = ppa.getEmbedPpa().getSandiJenis();
        cabang = ppa.getEmbedPpa().getCabang();
        wilayah = ppa.getEmbedPpa().getWilayah();
        mataUang = ppa.getEmbedPpa().getMataUang();
        tglJthTempo = ppa.getTglJthTempo();
        metodeUkur = ppa.getMetodeUkur();
        kualitasAset = ppa.getKualitasAset();
        nomorAset = ppa.getNomorAset();
        hargaPerolehan = ppa.getHargaPerolehan();
        akumulasiSusut = ppa.getAkumulasiSusut();
        nilaiBuku = ppa.getNilaiBuku();
        statusAset = ppa.getStatusAset();
    }

    public PpaResponseDTO() {

    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa;
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

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
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

