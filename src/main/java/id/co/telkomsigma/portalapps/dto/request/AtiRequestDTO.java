package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class AtiRequestDTO implements Serializable {

    private static final long serialVersionUID = 6190335901841255623L;

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
    private boolean isNew;

    public String getNomorAset() {
        return nomorAset;
    }

    public void setNomorAset(String nomorAset) {
        this.nomorAset = nomorAset.toUpperCase();
    }

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

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta.toUpperCase();
    }

    public String getSumberPerolehan() {
        return sumberPerolehan;
    }

    public void setSumberPerolehan(String sumberPerolehan) {
        this.sumberPerolehan = sumberPerolehan.toUpperCase();
    }

    public String getMetodeUkur() {
        return metodeUkur;
    }

    public void setMetodeUkur(String metodeUkur) {
        this.metodeUkur = metodeUkur.toUpperCase();
    }

    public String getStatusAset() {
        return statusAset;
    }

    public void setStatusAset(String statusAset) {
        this.statusAset = statusAset.toUpperCase();
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

    public String getAkumulasiSusut() {
        return akumulasiSusut;
    }

    public void setAkumulasiSusut(String akumulasiSusut) {
        this.akumulasiSusut = akumulasiSusut.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
