package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class PpaRequestDTO implements Serializable {

    private static final long serialVersionUID = -9161927849555938195L;

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
    private boolean isNew;

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa.toUpperCase();
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa.toUpperCase();
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis.toUpperCase();
    }

    public String getSandiJenis() {
        return sandiJenis;
    }

    public void setSandiJenis(String sandiJenis) {
        this.sandiJenis = sandiJenis.toUpperCase();
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang.toUpperCase();
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah.toUpperCase();
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang.toUpperCase();
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai.toUpperCase();
    }

    public String getTglJthTempo() {
        return tglJthTempo;
    }

    public void setTglJthTempo(String tglJthTempo) {
        this.tglJthTempo = tglJthTempo.toUpperCase();
    }

    public String getMetodeUkur() {
        return metodeUkur;
    }

    public void setMetodeUkur(String metodeUkur) {
        this.metodeUkur = metodeUkur.toUpperCase();
    }

    public String getStatusAset() {
        return statusAset;
    }

    public void setStatusAset(String statusAset) {
        this.statusAset = statusAset.toUpperCase();
    }

    public String getKualitasAset() {
        return kualitasAset;
    }

    public void setKualitasAset(String kualitasAset) {
        this.kualitasAset = kualitasAset.toUpperCase();
    }

    public String getNomorAset() {
        return nomorAset;
    }

    public void setNomorAset(String nomorAset) {
        this.nomorAset = nomorAset.toUpperCase();
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}

