package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.AtbRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedAtb;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelatb")
public class Atb {

    @EmbeddedId
    private EmbedAtb embedAtb;

    @Column(name = "tgl_jth_tempo")
    private String jthTempo;

    @Column(name = "jenis_valuta")
    private String jenisValuta;

    @Column(name = "metode_ukur")
    private String metodeUkur;

    @Column(name = "jml_bln_lalu")
    private String jmlBlnLalu;

    @Column(name = "jml_debet")
    private String jmlDebet;

    @Column(name = "jml_kredit")
    private String jmlKredit;

    @Column(name = "jml_lainnya")
    private String jmlLainnya;

    @Column(name = "jml_bln_laporan")
    private String jmlBlnLaporan;

    @Column(name = "akumulasi_amortisasi")
    private String akumulasiAmortasi;

    public Atb() {
    }

    public Atb(EmbedAtb embedAtb, AtbRequestDTO atbRequestDTO) {
        this.embedAtb = embedAtb;
        jthTempo = atbRequestDTO.getJthTempo();
        jenisValuta = atbRequestDTO.getJenisValuta();
        metodeUkur = atbRequestDTO.getMetodeUkur();
        jmlBlnLalu = atbRequestDTO.getJmlBlnLalu();
        jmlDebet = atbRequestDTO.getJmlDebet();
        jmlKredit = atbRequestDTO.getJmlKredit();
        jmlLainnya = atbRequestDTO.getJmlLainnya();
        jmlBlnLaporan = atbRequestDTO.getJmlBlnLaporan();
        akumulasiAmortasi = atbRequestDTO.getAkumulasiAmortasi();
    }

    public EmbedAtb getEmbedAtb() {
        return embedAtb;
    }

    public void setEmbedAtb(EmbedAtb embedAtb) {
        this.embedAtb = embedAtb;
    }

    public String getJthTempo() {
        return jthTempo;
    }

    public void setJthTempo(String jthTempo) {
        this.jthTempo = jthTempo;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }

    public String getMetodeUkur() {
        return metodeUkur;
    }

    public void setMetodeUkur(String metodeUkur) {
        this.metodeUkur = metodeUkur;
    }

    public String getJmlBlnLalu() {
        return jmlBlnLalu;
    }

    public void setJmlBlnLalu(String jmlBlnLalu) {
        this.jmlBlnLalu = jmlBlnLalu;
    }

    public String getJmlDebet() {
        return jmlDebet;
    }

    public void setJmlDebet(String jmlDebet) {
        this.jmlDebet = jmlDebet;
    }

    public String getJmlKredit() {
        return jmlKredit;
    }

    public void setJmlKredit(String jmlKredit) {
        this.jmlKredit = jmlKredit;
    }

    public String getJmlLainnya() {
        return jmlLainnya;
    }

    public void setJmlLainnya(String jmlLainnya) {
        this.jmlLainnya = jmlLainnya;
    }

    public String getJmlBlnLaporan() {
        return jmlBlnLaporan;
    }

    public void setJmlBlnLaporan(String jmlBlnLaporan) {
        this.jmlBlnLaporan = jmlBlnLaporan;
    }

    public String getAkumulasiAmortasi() {
        return akumulasiAmortasi;
    }

    public void setAkumulasiAmortasi(String akumulasiAmortasi) {
        this.akumulasiAmortasi = akumulasiAmortasi;
    }
}
