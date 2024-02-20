package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class AsetLainnyaRequestDTO implements Serializable {

    private static final long serialVersionUID = 2091082682302603832L;

    private String kodeCoa;
    private String keteranganJenisLainnya;
    private String jenisAset;
    private String golonganDebitur;
    private String hubunganDebitur;
    private String pendBknPend;
    private boolean isNew;

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa.toUpperCase();
    }

    public String getKeteranganJenisLainnya() {
        return keteranganJenisLainnya;
    }

    public void setKeteranganJenisLainnya(String keteranganJenisLainnya) {
        this.keteranganJenisLainnya = keteranganJenisLainnya.toUpperCase();
    }

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset.toUpperCase();
    }

    public String getGolonganDebitur() {
        return golonganDebitur;
    }

    public void setGolonganDebitur(String golonganDebitur) {
        this.golonganDebitur = golonganDebitur.toUpperCase();
    }

    public String getHubunganDebitur() {
        return hubunganDebitur;
    }

    public void setHubunganDebitur(String hubunganDebitur) {
        this.hubunganDebitur = hubunganDebitur.toUpperCase();
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
