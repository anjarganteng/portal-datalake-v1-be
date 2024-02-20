package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Atb;

/**
 * @author satiya
 */
public class AtbResponseDTO {

    private static final long serialVersionUID = -5219245212900425250L;

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

    public AtbResponseDTO() {
    }

    public AtbResponseDTO(Atb atb) {
        jenisAset = atb.getEmbedAtb().getJenisAset();
        tglPerolehan = atb.getEmbedAtb().getTglPerolehan();
        tglMulai = atb.getEmbedAtb().getTglMulai();
        jthTempo = atb.getJthTempo();
        jenisValuta = atb.getJenisValuta();
        metodeUkur = atb.getMetodeUkur();
        jmlBlnLalu = atb.getJmlBlnLalu();
        jmlDebet = atb.getJmlDebet();
        jmlKredit = atb.getJmlKredit();
        jmlLainnya = atb.getJmlLainnya();
        jmlBlnLaporan = atb.getJmlBlnLaporan();
        akumulasiAmortasi = atb.getAkumulasiAmortasi();
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

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
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
