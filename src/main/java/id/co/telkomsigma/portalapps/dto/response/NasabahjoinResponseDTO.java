package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Nasabahjoin;

import java.sql.Date;

public class NasabahjoinResponseDTO {

    private static final long serialVersionUID = 79100512909750810L;

    private String cif;
    private String cifJoin;
    private String flagNasabah;
    private String flagJoin;
    private String tipeCif;
    private String nmLnkpNsb;
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

    public NasabahjoinResponseDTO() {
    }

    public NasabahjoinResponseDTO(Nasabahjoin nasabahjoin) {
        cif = nasabahjoin.getCif();
        cifJoin = nasabahjoin.getCifJoin();
        flagNasabah = nasabahjoin.getFlagNasabah();
        flagJoin = nasabahjoin.getFlagJoin();
        tipeCif = nasabahjoin.getTipeCif();
        nmLnkpNsb = nasabahjoin.getNmLnkpNsb();
        noNpwp = nasabahjoin.getNoNpwp();
        jnsIdentitas = nasabahjoin.getJnsIdentitas();
        noIdentitas = nasabahjoin.getNoIdentitas();
        nmIbuKdg = nasabahjoin.getNmIbuKdg();
        tmpLahir = nasabahjoin.getTmpLahir();
        tglLahir = nasabahjoin.getTglLahir();
        noSiup = nasabahjoin.getNoSiup();
        nmLnkpPemegangKuasa = nasabahjoin.getNmLnkpPemegangKuasa();
        jnsIdentitasPemegangKuasa = nasabahjoin.getJnsIdentitasPemegangKuasa();
        noIdentitasPemegangKuasa = nasabahjoin.getNoIdentitasPemegangKuasa();
        alamat = nasabahjoin.getAlamat();
        kabKota = nasabahjoin.getKabKota();
        kewarnegaraan = nasabahjoin.getKewarnegaraan();
        noTelp = nasabahjoin.getNoTelp();
        flagFraud = nasabahjoin.getFlagFraud();
        hubDgnBank = nasabahjoin.getHubDgnBank();
        golNsb = nasabahjoin.getGolNsb();
        kategoriUsaha = nasabahjoin.getKategoriUsaha();
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCifJoin() {
        return cifJoin;
    }

    public void setCifJoin(String cifJoin) {
        this.cifJoin = cifJoin;
    }

    public String getFlagNasabah() {
        return flagNasabah;
    }

    public void setFlagNasabah(String flagNasabah) {
        this.flagNasabah = flagNasabah;
    }

    public String getFlagJoin() {
        return flagJoin;
    }

    public void setFlagJoin(String flagJoin) {
        this.flagJoin = flagJoin;
    }

    public String getTipeCif() {
        return tipeCif;
    }

    public void setTipeCif(String tipeCif) {
        this.tipeCif = tipeCif;
    }

    public String getNmLnkpNsb() {
        return nmLnkpNsb;
    }

    public void setNmLnkpNsb(String nmLnkpNsb) {
        this.nmLnkpNsb = nmLnkpNsb;
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
