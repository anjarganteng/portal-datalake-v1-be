package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class PbiRequestDTO implements Serializable {

    private static final long serialVersionUID = -6230382103707469890L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String dalamLuarNegeri;
    private String jenis;
    private String sandiJenis;
    private String branch;
    private String wilayah;
    private String mataUang;
    private String hubDgnBank;
    private String sandiBank;
    private String noRekening;
    private String mulai;
    private String jatuhTempo;
    private String klasifikasiAset;
    private String kualitasAset;
    private BigDecimal sukuBunga;
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

    public String getDalamLuarNegeri() {
        return dalamLuarNegeri;
    }

    public void setDalamLuarNegeri(String dalamLuarNegeri) {
        this.dalamLuarNegeri = dalamLuarNegeri.toUpperCase();
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch.toUpperCase();
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

    public String getHubDgnBank() {
        return hubDgnBank;
    }

    public void setHubDgnBank(String hubDgnBank) {
        this.hubDgnBank = hubDgnBank.toUpperCase();
    }

    public String getSandiBank() {
        return sandiBank;
    }

    public void setSandiBank(String sandiBank) {
        this.sandiBank = sandiBank.toUpperCase();
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening.toUpperCase();
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai.toUpperCase();
    }

    public String getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(String jatuhTempo) {
        this.jatuhTempo = jatuhTempo.toUpperCase();
    }

    public String getKlasifikasiAset() {
        return klasifikasiAset;
    }

    public void setKlasifikasiAset(String klasifikasiAset) {
        this.klasifikasiAset = klasifikasiAset.toUpperCase();
    }

    public String getKualitasAset() {
        return kualitasAset;
    }

    public void setKualitasAset(String kualitasAset) {
        this.kualitasAset = kualitasAset.toUpperCase();
    }

    public BigDecimal getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(BigDecimal sukuBunga) {
        this.sukuBunga = sukuBunga;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
