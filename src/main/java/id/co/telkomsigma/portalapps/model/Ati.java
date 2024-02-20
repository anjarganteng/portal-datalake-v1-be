package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.AtiRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelati")
public class Ati {

    @Id
    @Column(name = "nomor_aset")
    private String nomorAset;

    @Column(name = "jenis_aset")
    private String jenisAset;

    @Column(name = "tgl_perolehan")
    private String tglPerolehan;

    @Column(name = "jenis_valuta")
    private String jenisValuta;

    @Column(name = "sumber_perolehan")
    private String sumberPerolehan;

    @Column(name = "metode_ukur")
    private String metodeUkur;

    @Column(name = "status_aset")
    private String statusAset;

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

    @Column(name = "akumulasi_susut")
    private String akumulasiSusut;

    public Ati() {
    }

    public Ati(AtiRequestDTO atiRequestDTO) {
        nomorAset = atiRequestDTO.getNomorAset();
        jenisAset = atiRequestDTO.getJenisAset();
        tglPerolehan = atiRequestDTO.getTglPerolehan();
        jenisValuta = atiRequestDTO.getJenisValuta();
        sumberPerolehan = atiRequestDTO.getSumberPerolehan();
        metodeUkur = atiRequestDTO.getMetodeUkur();
        statusAset = atiRequestDTO.getStatusAset();
        jmlBlnLalu = atiRequestDTO.getJmlBlnLalu();
        jmlDebet = atiRequestDTO.getJmlDebet();
        jmlKredit = atiRequestDTO.getJmlKredit();
        jmlLainnya = atiRequestDTO.getJmlLainnya();
        jmlBlnLaporan = atiRequestDTO.getJmlBlnLaporan();
        akumulasiSusut = atiRequestDTO.getAkumulasiSusut();
    }

    public String getNomorAset() {
        return nomorAset;
    }

    public void setNomorAset(String nomorAset) {
        this.nomorAset = nomorAset;
    }

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset;
    }

    public String getTglPerolehan() {
        return tglPerolehan;
    }

    public void setTglPerolehan(String tglPerolehan) {
        this.tglPerolehan = tglPerolehan;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }

    public String getSumberPerolehan() {
        return sumberPerolehan;
    }

    public void setSumberPerolehan(String sumberPerolehan) {
        this.sumberPerolehan = sumberPerolehan;
    }

    public String getMetodeUkur() {
        return metodeUkur;
    }

    public void setMetodeUkur(String metodeUkur) {
        this.metodeUkur = metodeUkur;
    }

    public String getStatusAset() {
        return statusAset;
    }

    public void setStatusAset(String statusAset) {
        this.statusAset = statusAset;
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

    public String getAkumulasiSusut() {
        return akumulasiSusut;
    }

    public void setAkumulasiSusut(String akumulasiSusut) {
        this.akumulasiSusut = akumulasiSusut;
    }
}
