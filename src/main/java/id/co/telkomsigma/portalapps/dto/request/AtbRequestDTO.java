package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class AtbRequestDTO implements Serializable {

    private static final long serialVersionUID = -204227719878646476L;

    private String jenisAset;
    private String tglPerolehan;
    private String tglMulai;
    private String jthTempo;
    private String jenisValuta;
    private String metodeUkur;
    private String jmlBlnLalu;
    private String jmlDebet;
    private String jmlKredit;
    private String jmlLainnya;
    private String jmlBlnLaporan;
    private String akumulasiAmortasi;
    private boolean isNew;

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset.toUpperCase();
    }

    public String getTglPerolehan() {
        return tglPerolehan;
    }

    public void setTglPerolehan(String tglPerolehan) {
        this.tglPerolehan = tglPerolehan.toUpperCase();
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai.toUpperCase();
    }

    public String getJthTempo() {
        return jthTempo;
    }

    public void setJthTempo(String jthTempo) {
        this.jthTempo = jthTempo.toUpperCase();
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta.toUpperCase();
    }

    public String getMetodeUkur() {
        return metodeUkur;
    }

    public void setMetodeUkur(String metodeUkur) {
        this.metodeUkur = metodeUkur.toUpperCase();
    }

    public String getJmlBlnLalu() {
        return jmlBlnLalu;
    }

    public void setJmlBlnLalu(String jmlBlnLalu) {
        this.jmlBlnLalu = jmlBlnLalu.toUpperCase();
    }

    public String getJmlDebet() {
        return jmlDebet;
    }

    public void setJmlDebet(String jmlDebet) {
        this.jmlDebet = jmlDebet.toUpperCase();
    }

    public String getJmlKredit() {
        return jmlKredit;
    }

    public void setJmlKredit(String jmlKredit) {
        this.jmlKredit = jmlKredit.toUpperCase();
    }

    public String getJmlLainnya() {
        return jmlLainnya;
    }

    public void setJmlLainnya(String jmlLainnya) {
        this.jmlLainnya = jmlLainnya.toUpperCase();
    }

    public String getJmlBlnLaporan() {
        return jmlBlnLaporan;
    }

    public void setJmlBlnLaporan(String jmlBlnLaporan) {
        this.jmlBlnLaporan = jmlBlnLaporan.toUpperCase();
    }

    public String getAkumulasiAmortasi() {
        return akumulasiAmortasi;
    }

    public void setAkumulasiAmortasi(String akumulasiAmortasi) {
        this.akumulasiAmortasi = akumulasiAmortasi.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
