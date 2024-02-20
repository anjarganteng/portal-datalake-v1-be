package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.NasabahjoinRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelnasabahjoin")
public class Nasabahjoin {

    @Id
    @Column(name = "CIF")
    private String cif;

    @Column(name = "CIF_JOIN")
    private String cifJoin;

    @Column(name = "FLAG_NASABAH")
    private String flagNasabah;

    @Column(name = "FLAG_JOIN")
    private String flagJoin;

    @Column(name = "TIPE_CIF")
    private String tipeCif;

    @Column(name = "NM_LNKP_NSB")
    private String nmLnkpNsb;

    @Column(name = "NO_NPWP")
    private String noNpwp;

    @Column(name = "JNS_IDENTITAS")
    private String jnsIdentitas;

    @Column(name = "NO_IDENTITAS")
    private String noIdentitas;

    @Column(name = "NM_IBU_KDG")
    private String nmIbuKdg;

    @Column(name = "TMP_LAHIR")
    private String tmpLahir;

    @Column(name = "TGL_LAHIR")
    private Date tglLahir;

    @Column(name = "NO_SIUP")
    private String noSiup;

    @Column(name = "NM_LNKP_PEMEGANG_KUASA")
    private String nmLnkpPemegangKuasa;

    @Column(name = "JNS_IDENTITAS_PEMEGANG_KUASA")
    private String jnsIdentitasPemegangKuasa;

    @Column(name = "NO_IDENTITAS_PEMEGANG_KUASA")
    private String noIdentitasPemegangKuasa;

    @Column(name = "ALAMAT")
    private String alamat;

    @Column(name = "KAB_KOTA")
    private String kabKota;

    @Column(name = "KEWARGANEGARAAN")
    private String kewarnegaraan;

    @Column(name = "NO_TELP")
    private String noTelp;

    @Column(name = "FLAG_FRAUD")
    private String flagFraud;

    @Column(name = "HUB_DGN_BANK")
    private String hubDgnBank;

    @Column(name = "GOL_NSB")
    private String golNsb;

    @Column(name = "KATEGORI_USAHA")
    private String kategoriUsaha;

    public Nasabahjoin() {
    }

    public Nasabahjoin(NasabahjoinRequestDTO nasabahjoinRequestDTO) {
        cif = nasabahjoinRequestDTO.getCif();
        cifJoin = nasabahjoinRequestDTO.getCifJoin();
        flagNasabah = nasabahjoinRequestDTO.getFlagNasabah();
        flagJoin = nasabahjoinRequestDTO.getFlagJoin();
        tipeCif = nasabahjoinRequestDTO.getTipeCif();
        nmLnkpNsb = nasabahjoinRequestDTO.getNmLnkpNsb();
        noNpwp = nasabahjoinRequestDTO.getNoNpwp();
        jnsIdentitas = nasabahjoinRequestDTO.getJnsIdentitas();
        noIdentitas = nasabahjoinRequestDTO.getNoIdentitas();
        nmIbuKdg = nasabahjoinRequestDTO.getNmIbuKdg();
        tmpLahir = nasabahjoinRequestDTO.getTmpLahir();
        tglLahir = nasabahjoinRequestDTO.getTglLahir();
        noSiup = nasabahjoinRequestDTO.getNoSiup();
        nmLnkpPemegangKuasa = nasabahjoinRequestDTO.getNmLnkpPemegangKuasa();
        jnsIdentitasPemegangKuasa = nasabahjoinRequestDTO.getJnsIdentitasPemegangKuasa();
        noIdentitasPemegangKuasa = nasabahjoinRequestDTO.getNoIdentitasPemegangKuasa();
        alamat = nasabahjoinRequestDTO.getAlamat();
        kabKota = nasabahjoinRequestDTO.getKabKota();
        kewarnegaraan = nasabahjoinRequestDTO.getKewarnegaraan();
        noTelp = nasabahjoinRequestDTO.getNoTelp();
        flagFraud = nasabahjoinRequestDTO.getFlagFraud();
        hubDgnBank = nasabahjoinRequestDTO.getHubDgnBank();
        golNsb = nasabahjoinRequestDTO.getGolNsb();
        kategoriUsaha = nasabahjoinRequestDTO.getKategoriUsaha();
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
