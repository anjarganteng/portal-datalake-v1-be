package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.PihakLawan;

import java.sql.Date;

public class PihakLawanResponseDTO {

    private String kdCabang;
    private String idPihakLawan;
    private String jenisIdentitas;
    private String nomorId;
    private String jenisKelamin;
    private String namaLengkap;
    private String npwp;
    private String kodeWn;
    private String kodeNegara;
    private String jenisKegUsaha;
    private String hubBank;
    private String golDeb;
    private String kdLembaga;
    private String kdRating;
    private Date tglRating;
    private String tahun;
    private String kota;
    private String idGroup;

    public PihakLawanResponseDTO() {
    }

    public PihakLawanResponseDTO(PihakLawan pihakLawan) {
        kdCabang = pihakLawan.getEmbedPihakLawan().getKdCabang();
        idPihakLawan = pihakLawan.getEmbedPihakLawan().getIdPihakLawan();
        jenisIdentitas = pihakLawan.getJenisIdentitas();
        nomorId = pihakLawan.getNomorId();
        jenisKelamin = pihakLawan.getJenisKelamin();
        namaLengkap = pihakLawan.getNamaLengkap();
        npwp = pihakLawan.getNpwp();
        kodeWn = pihakLawan.getKodeWn();
        kodeNegara = pihakLawan.getKodeNegara();
        jenisKegUsaha = pihakLawan.getJenisKegUsaha();
        hubBank = pihakLawan.getHubBank();
        golDeb = pihakLawan.getGolDeb();
        kdLembaga = pihakLawan.getKdLembaga();
        kdRating = pihakLawan.getKdRating();
        tglRating = pihakLawan.getTglRating();
        tahun = pihakLawan.getTahun();
        kota = pihakLawan.getKota();
        idGroup = pihakLawan.getIdGroup();
    }

    public String getKdCabang() {
        return kdCabang;
    }

    public void setKdCabang(String kdCabang) {
        this.kdCabang = kdCabang;
    }

    public String getIdPihakLawan() {
        return idPihakLawan;
    }

    public void setIdPihakLawan(String idPihakLawan) {
        this.idPihakLawan = idPihakLawan;
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas;
    }

    public String getNomorId() {
        return nomorId;
    }

    public void setNomorId(String nomorId) {
        this.nomorId = nomorId;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getKodeWn() {
        return kodeWn;
    }

    public void setKodeWn(String kodeWn) {
        this.kodeWn = kodeWn;
    }

    public String getKodeNegara() {
        return kodeNegara;
    }

    public void setKodeNegara(String kodeNegara) {
        this.kodeNegara = kodeNegara;
    }

    public String getJenisKegUsaha() {
        return jenisKegUsaha;
    }

    public void setJenisKegUsaha(String jenisKegUsaha) {
        this.jenisKegUsaha = jenisKegUsaha;
    }

    public String getHubBank() {
        return hubBank;
    }

    public void setHubBank(String hubBank) {
        this.hubBank = hubBank;
    }

    public String getGolDeb() {
        return golDeb;
    }

    public void setGolDeb(String golDeb) {
        this.golDeb = golDeb;
    }

    public String getKdLembaga() {
        return kdLembaga;
    }

    public void setKdLembaga(String kdLembaga) {
        this.kdLembaga = kdLembaga;
    }

    public String getKdRating() {
        return kdRating;
    }

    public void setKdRating(String kdRating) {
        this.kdRating = kdRating;
    }

    public Date getTglRating() {
        return tglRating;
    }

    public void setTglRating(Date tglRating) {
        this.tglRating = tglRating;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }
}
