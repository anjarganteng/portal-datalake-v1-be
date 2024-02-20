package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author satiya
 */
public class NasabahjoinRequestDTO implements Serializable {

    private static final long serialVersionUID = 4931142381278064197L;

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
    private boolean isNew;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif.toUpperCase();
    }

    public String getCifJoin() {
        return cifJoin;
    }

    public void setCifJoin(String cifJoin) {
        this.cifJoin = cifJoin.toUpperCase();
    }

    public String getFlagNasabah() {
        return flagNasabah;
    }

    public void setFlagNasabah(String flagNasabah) {
        this.flagNasabah = flagNasabah.toUpperCase();
    }

    public String getFlagJoin() {
        return flagJoin;
    }

    public void setFlagJoin(String flagJoin) {
        this.flagJoin = flagJoin.toUpperCase();
    }

    public String getTipeCif() {
        return tipeCif;
    }

    public void setTipeCif(String tipeCif) {
        this.tipeCif = tipeCif.toUpperCase();
    }

    public String getNmLnkpNsb() {
        return nmLnkpNsb;
    }

    public void setNmLnkpNsb(String nmLnkpNsb) {
        this.nmLnkpNsb = nmLnkpNsb.toUpperCase();
    }

    public String getNoNpwp() {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp.toUpperCase();
    }

    public String getJnsIdentitas() {
        return jnsIdentitas;
    }

    public void setJnsIdentitas(String jnsIdentitas) {
        this.jnsIdentitas = jnsIdentitas.toUpperCase();
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas.toUpperCase();
    }

    public String getNmIbuKdg() {
        return nmIbuKdg;
    }

    public void setNmIbuKdg(String nmIbuKdg) {
        this.nmIbuKdg = nmIbuKdg.toUpperCase();
    }

    public String getTmpLahir() {
        return tmpLahir;
    }

    public void setTmpLahir(String tmpLahir) {
        this.tmpLahir = tmpLahir.toUpperCase();
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
        this.noSiup = noSiup.toUpperCase();
    }

    public String getNmLnkpPemegangKuasa() {
        return nmLnkpPemegangKuasa;
    }

    public void setNmLnkpPemegangKuasa(String nmLnkpPemegangKuasa) {
        this.nmLnkpPemegangKuasa = nmLnkpPemegangKuasa.toUpperCase();
    }

    public String getJnsIdentitasPemegangKuasa() {
        return jnsIdentitasPemegangKuasa;
    }

    public void setJnsIdentitasPemegangKuasa(String jnsIdentitasPemegangKuasa) {
        this.jnsIdentitasPemegangKuasa = jnsIdentitasPemegangKuasa.toUpperCase();
    }

    public String getNoIdentitasPemegangKuasa() {
        return noIdentitasPemegangKuasa;
    }

    public void setNoIdentitasPemegangKuasa(String noIdentitasPemegangKuasa) {
        this.noIdentitasPemegangKuasa = noIdentitasPemegangKuasa.toUpperCase();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat.toUpperCase();
    }

    public String getKabKota() {
        return kabKota;
    }

    public void setKabKota(String kabKota) {
        this.kabKota = kabKota.toUpperCase();
    }

    public String getKewarnegaraan() {
        return kewarnegaraan;
    }

    public void setKewarnegaraan(String kewarnegaraan) {
        this.kewarnegaraan = kewarnegaraan.toUpperCase();
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp.toUpperCase();
    }

    public String getFlagFraud() {
        return flagFraud;
    }

    public void setFlagFraud(String flagFraud) {
        this.flagFraud = flagFraud.toUpperCase();
    }

    public String getHubDgnBank() {
        return hubDgnBank;
    }

    public void setHubDgnBank(String hubDgnBank) {
        this.hubDgnBank = hubDgnBank.toUpperCase();
    }

    public String getGolNsb() {
        return golNsb;
    }

    public void setGolNsb(String golNsb) {
        this.golNsb = golNsb.toUpperCase();
    }

    public String getKategoriUsaha() {
        return kategoriUsaha;
    }

    public void setKategoriUsaha(String kategoriUsaha) {
        this.kategoriUsaha = kategoriUsaha.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
