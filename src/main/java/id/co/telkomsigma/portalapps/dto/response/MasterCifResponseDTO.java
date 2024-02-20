package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.MasterCif;

import java.sql.Date;

/**
 * @author satiya
 */
public class MasterCifResponseDTO {

    private static final long serialVersionUID = -1856625078451071713L;

    private String cif;
    private String nmLnkpNsb;
    private String tipeNasabah;
    private String noNpwp;
    private String jnsIdentitas;
    private String noIdentitas;
    private String nmIbuKdg;
    private String tmpLahir;
    private Date tglLahir;
    private String noSiup;
    private String nmLnkpPemegangKuasa;
    private String jnsIdentitasPemegangKuasa;
    private String noIdentitasPemegangKuasa;
    private String alamat;
    private String kabKota;
    private String kewarnegaraan;
    private String noTelp;
    private String flagFraud;
    private String hubDgnBank;
    private String golNsb;
    private String kategoriUsaha;

    public MasterCifResponseDTO() {
    }

    public MasterCifResponseDTO(MasterCif masterCif) {
        cif = masterCif.getCif();
        nmLnkpNsb = masterCif.getNmLnkpNsb();
        tipeNasabah = masterCif.getTipeNasabah();
        noNpwp = masterCif.getNoNpwp();
        jnsIdentitas = masterCif.getJnsIdentitas();
        noIdentitas = masterCif.getNoIdentitas();
        nmIbuKdg = masterCif.getNmIbuKdg();
        tmpLahir = masterCif.getTmpLahir();
        tglLahir = masterCif.getTglLahir();
        noSiup = masterCif.getNoSiup();
        nmLnkpPemegangKuasa = masterCif.getNmLnkpPemegangKuasa();
        jnsIdentitasPemegangKuasa = masterCif.getJnsIdentitasPemegangKuasa();
        noIdentitasPemegangKuasa = masterCif.getNoIdentitasPemegangKuasa();
        alamat = masterCif.getAlamat();
        kabKota = masterCif.getKabKota();
        kewarnegaraan = masterCif.getKewarnegaraan();
        noTelp = masterCif.getNoTelp();
        flagFraud = masterCif.getFlagFraud();
        hubDgnBank = masterCif.getHubDgnBank();
        golNsb = masterCif.getGolNsb();
        kategoriUsaha = masterCif.getKategoriUsaha();
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNmLnkpNsb() {
        return nmLnkpNsb;
    }

    public void setNmLnkpNsb(String nmLnkpNsb) {
        this.nmLnkpNsb = nmLnkpNsb;
    }

    public String getTipeNasabah() {
        return tipeNasabah;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
    }

    public String getNoNpwp() {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
    }

    public String getJnsIdentitas() {
        return jnsIdentitas;
    }

    public void setJnsIdentitas(String jnsIdentitas) {
        this.jnsIdentitas = jnsIdentitas;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getNmIbuKdg() {
        return nmIbuKdg;
    }

    public void setNmIbuKdg(String nmIbuKdg) {
        this.nmIbuKdg = nmIbuKdg;
    }

    public String getTmpLahir() {
        return tmpLahir;
    }

    public void setTmpLahir(String tmpLahir) {
        this.tmpLahir = tmpLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoSiup() {
        return noSiup;
    }

    public void setNoSiup(String noSiup) {
        this.noSiup = noSiup;
    }

    public String getNmLnkpPemegangKuasa() {
        return nmLnkpPemegangKuasa;
    }

    public void setNmLnkpPemegangKuasa(String nmLnkpPemegangKuasa) {
        this.nmLnkpPemegangKuasa = nmLnkpPemegangKuasa;
    }

    public String getJnsIdentitasPemegangKuasa() {
        return jnsIdentitasPemegangKuasa;
    }

    public void setJnsIdentitasPemegangKuasa(String jnsIdentitasPemegangKuasa) {
        this.jnsIdentitasPemegangKuasa = jnsIdentitasPemegangKuasa;
    }

    public String getNoIdentitasPemegangKuasa() {
        return noIdentitasPemegangKuasa;
    }

    public void setNoIdentitasPemegangKuasa(String noIdentitasPemegangKuasa) {
        this.noIdentitasPemegangKuasa = noIdentitasPemegangKuasa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKabKota() {
        return kabKota;
    }

    public void setKabKota(String kabKota) {
        this.kabKota = kabKota;
    }

    public String getKewarnegaraan() {
        return kewarnegaraan;
    }

    public void setKewarnegaraan(String kewarnegaraan) {
        this.kewarnegaraan = kewarnegaraan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getFlagFraud() {
        return flagFraud;
    }

    public void setFlagFraud(String flagFraud) {
        this.flagFraud = flagFraud;
    }

    public String getHubDgnBank() {
        return hubDgnBank;
    }

    public void setHubDgnBank(String hubDgnBank) {
        this.hubDgnBank = hubDgnBank;
    }

    public String getGolNsb() {
        return golNsb;
    }

    public void setGolNsb(String golNsb) {
        this.golNsb = golNsb;
    }

    public String getKategoriUsaha() {
        return kategoriUsaha;
    }

    public void setKategoriUsaha(String kategoriUsaha) {
        this.kategoriUsaha = kategoriUsaha;
    }
}
