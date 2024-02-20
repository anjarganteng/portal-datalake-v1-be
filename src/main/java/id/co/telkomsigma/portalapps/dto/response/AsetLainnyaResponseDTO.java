package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.AsetLainnya;

/**
 * @author satiya
 */
public class AsetLainnyaResponseDTO {

    private static final long serialVersionUID = 3893134705411042834L;

    private String kodeCoa;
    private String keteranganJenisLainnya;
    private String jenisAset;
    private String golonganDebitur;
    private String hubunganDebitur;
    private String pendBknPend;

    public AsetLainnyaResponseDTO() {
    }

    public AsetLainnyaResponseDTO(AsetLainnya asetLainnya) {
        kodeCoa = asetLainnya.getKodeCoa();
        keteranganJenisLainnya = asetLainnya.getKeteranganJenisLainnya();
        jenisAset = asetLainnya.getJenisAset();
        golonganDebitur = asetLainnya.getGolonganDebitur();
        hubunganDebitur = asetLainnya.getHubunganDebitur();
        pendBknPend = asetLainnya.getPendBknPend();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getKeteranganJenisLainnya() {
        return keteranganJenisLainnya;
    }

    public void setKeteranganJenisLainnya(String keteranganJenisLainnya) {
        this.keteranganJenisLainnya = keteranganJenisLainnya;
    }

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset;
    }

    public String getGolonganDebitur() {
        return golonganDebitur;
    }

    public void setGolonganDebitur(String golonganDebitur) {
        this.golonganDebitur = golonganDebitur;
    }

    public String getHubunganDebitur() {
        return hubunganDebitur;
    }

    public void setHubunganDebitur(String hubunganDebitur) {
        this.hubunganDebitur = hubunganDebitur;
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend;
    }
}
