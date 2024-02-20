package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Pbi;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class PbiResponseDTO {

    private static final long serialVersionUID = 4663565074964466030L;

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

    public PbiResponseDTO() {
    }

    public PbiResponseDTO(Pbi pbi) {
        kodeCoa = pbi.getEmbedPbi().getKodeCoa();
        mataUang = pbi.getEmbedPbi().getMataUang();
        mulai = pbi.getEmbedPbi().getMulai();
        deskripsiCoa = pbi.getDeskripsiCoa();
        dalamLuarNegeri = pbi.getDalamLuarNegeri();
        jenis = pbi.getJenis();
        sandiJenis = pbi.getSandiJenis();
        branch = pbi.getBranch();
        wilayah = pbi.getWilayah();
        hubDgnBank = pbi.getHubDgnBank();
        sandiBank = pbi.getSandiBank();
        noRekening = pbi.getNoRekening();
        jatuhTempo = pbi.getJatuhTempo();
        klasifikasiAset = pbi.getKlasifikasiAset();
        kualitasAset = pbi.getKualitasAset();
        sukuBunga = pbi.getSukuBunga();
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

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
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

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
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
