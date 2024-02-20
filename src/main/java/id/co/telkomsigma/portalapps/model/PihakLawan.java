package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PihakLawanRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedPihakLawan;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpihaklawan")
public class PihakLawan {


    @EmbeddedId
    private EmbedPihakLawan embedPihakLawan;

    @Column(name = "JENIS_INDENTITAS")
    private String jenisIdentitas;

    @Column(name = "NOMOR_ID")
    private String nomorId;

    @Column(name = "JENIS_KELAMIN")
    private String jenisKelamin;

    @Column(name = "NAMA_LENGKAP")
    private String namaLengkap;

    @Column(name = "NPWP")
    private String npwp;

    @Column(name = "KODE_WN")
    private String kodeWn;

    @Column(name = "KODE_NEGARA")
    private String kodeNegara;

    @Column(name = "JENIS_KEG_USAHA")
    private String jenisKegUsaha;

    @Column(name = "HUB_BANK")
    private String hubBank;

    @Column(name = "GOL_DEB")
    private String golDeb;

    @Column(name = "KD_LEMBAGA")
    private String kdLembaga;

    @Column(name = "KD_RATING")
    private String kdRating;

    @Column(name = "TGL_RATING")
    private Date tglRating;

    @Column(name = "TAHUN")
    private String tahun;

    @Column(name = "KOTA")
    private String kota;

    @Column(name = "ID_GROUP")
    private String idGroup;

    public PihakLawan() {
    }

    public PihakLawan(EmbedPihakLawan embedPihakLawan, PihakLawanRequestDTO pihakLawanRequestDTO) {
        this.embedPihakLawan = embedPihakLawan;
        jenisIdentitas = pihakLawanRequestDTO.getJenisIdentitas();
        nomorId = pihakLawanRequestDTO.getNomorId();
        jenisKelamin = pihakLawanRequestDTO.getJenisKelamin();
        namaLengkap = pihakLawanRequestDTO.getNamaLengkap();
        npwp = pihakLawanRequestDTO.getNpwp();
        kodeWn = pihakLawanRequestDTO.getKodeWn();
        kodeNegara = pihakLawanRequestDTO.getKodeNegara();
        jenisKegUsaha = pihakLawanRequestDTO.getJenisKegUsaha();
        hubBank = pihakLawanRequestDTO.getHubBank();
        golDeb = pihakLawanRequestDTO.getGolDeb();
        kdLembaga = pihakLawanRequestDTO.getKdLembaga();
        kdRating = pihakLawanRequestDTO.getKdRating();
        tglRating = pihakLawanRequestDTO.getTglRating();
        tahun = pihakLawanRequestDTO.getTahun();
        kota = pihakLawanRequestDTO.getKota();
        idGroup = pihakLawanRequestDTO.getIdGroup();
    }

    public EmbedPihakLawan getEmbedPihakLawan() {
        return embedPihakLawan;
    }

    public void setEmbedPihakLawan(EmbedPihakLawan embedPihakLawan) {
        this.embedPihakLawan = embedPihakLawan;
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
