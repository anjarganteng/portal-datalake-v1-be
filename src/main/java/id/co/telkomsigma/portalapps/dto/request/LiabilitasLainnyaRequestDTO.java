package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class LiabilitasLainnyaRequestDTO implements Serializable {

    private static final long serialVersionUID = 4518723805323178291L;

    private String kodeCoa;
    private String keterangan;
    private String jenisLiabilitas;
    private String golonganKreditur;
    private String hubunganKreditur;
    private String negaraKreditur;
    private String pendBknPend;
    private boolean isNew;

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa.toUpperCase();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan.toUpperCase();
    }

    public String getJenisLiabilitas() {
        return jenisLiabilitas;
    }

    public void setJenisLiabilitas(String jenisLiabilitas) {
        this.jenisLiabilitas = jenisLiabilitas.toUpperCase();
    }

    public String getGolonganKreditur() {
        return golonganKreditur;
    }

    public void setGolonganKreditur(String golonganKreditur) {
        this.golonganKreditur = golonganKreditur.toUpperCase();
    }

    public String getHubunganKreditur() {
        return hubunganKreditur;
    }

    public void setHubunganKreditur(String hubunganKreditur) {
        this.hubunganKreditur = hubunganKreditur.toUpperCase();
    }

    public String getNegaraKreditur() {
        return negaraKreditur;
    }

    public void setNegaraKreditur(String negaraKreditur) {
        this.negaraKreditur = negaraKreditur.toUpperCase();
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
