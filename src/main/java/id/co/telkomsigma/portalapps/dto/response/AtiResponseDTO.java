package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Ati;

/**
 * @author satiya
 */
public class AtiResponseDTO {

    private static final long serialVersionUID = -6544246097632971110L;

    private String nomorAset;
    private String jenisAset;
    private String tglPerolehan;
    private String jenisValuta;
    private String sumberPerolehan;
    private String metodeUkur;
    private String statusAset;
    private String jmlBlnLalu;
    private String jmlDebet;
    private String jmlKredit;
    private String jmlLainnya;
    private String jmlBlnLaporan;
    private String akumulasiSusut;

    public AtiResponseDTO() {
    }

    public AtiResponseDTO(Ati ati) {
        nomorAset = ati.getNomorAset();
        jenisAset = ati.getJenisAset();
        tglPerolehan = ati.getTglPerolehan();
        jenisValuta = ati.getJenisValuta();
        sumberPerolehan = ati.getSumberPerolehan();
        metodeUkur = ati.getMetodeUkur();
        statusAset = ati.getStatusAset();
        jmlBlnLalu = ati.getJmlBlnLalu();
        jmlDebet = ati.getJmlDebet();
        jmlKredit = ati.getJmlKredit();
        jmlLainnya = ati.getJmlLainnya();
        jmlBlnLaporan = ati.getJmlBlnLaporan();
        akumulasiSusut = ati.getAkumulasiSusut();
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
