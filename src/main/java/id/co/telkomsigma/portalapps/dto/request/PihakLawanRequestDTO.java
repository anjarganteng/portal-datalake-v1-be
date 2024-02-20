package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author satiya
 */
public class PihakLawanRequestDTO implements Serializable {

    private static final long serialVersionUID = 6007435853342514867L;

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
    private boolean isNew;

    public String getKdCabang() {
        return kdCabang;
    }

    public void setKdCabang(String kdCabang) {
        this.kdCabang = kdCabang.toUpperCase();
    }

    public String getIdPihakLawan() {
        return idPihakLawan;
    }

    public void setIdPihakLawan(String idPihakLawan) {
        this.idPihakLawan = idPihakLawan.toUpperCase();
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas.toUpperCase();
    }

    public String getNomorId() {
        return nomorId;
    }

    public void setNomorId(String nomorId) {
        this.nomorId = nomorId.toUpperCase();
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin.toUpperCase();
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap.toUpperCase();
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp.toUpperCase();
    }

    public String getKodeWn() {
        return kodeWn;
    }

    public void setKodeWn(String kodeWn) {
        this.kodeWn = kodeWn.toUpperCase();
    }

    public String getKodeNegara() {
        return kodeNegara;
    }

    public void setKodeNegara(String kodeNegara) {
        this.kodeNegara = kodeNegara.toUpperCase();
    }

    public String getJenisKegUsaha() {
        return jenisKegUsaha;
    }

    public void setJenisKegUsaha(String jenisKegUsaha) {
        this.jenisKegUsaha = jenisKegUsaha.toUpperCase();
    }

    public String getHubBank() {
        return hubBank;
    }

    public void setHubBank(String hubBank) {
        this.hubBank = hubBank.toUpperCase();
    }

    public String getGolDeb() {
        return golDeb;
    }

    public void setGolDeb(String golDeb) {
        this.golDeb = golDeb.toUpperCase();
    }

    public String getKdLembaga() {
        return kdLembaga;
    }

    public void setKdLembaga(String kdLembaga) {
        this.kdLembaga = kdLembaga.toUpperCase();
    }

    public String getKdRating() {
        return kdRating;
    }

    public void setKdRating(String kdRating) {
        this.kdRating = kdRating.toUpperCase();
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
        this.tahun = tahun.toUpperCase();
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota.toUpperCase();
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
