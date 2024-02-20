package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.LiabilitasLainnya;

/**
 * @author satiya
 */
public class LiabilitasLainnyaResponseDTO {

    private static final long serialVersionUID = 4161402121147217483L;

    private String kodeCoa;
    private String keterangan;
    private String jenisLiabilitas;
    private String golonganKreditur;
    private String hubunganKreditur;
    private String negaraKreditur;
    private String pendBknPend;

    public LiabilitasLainnyaResponseDTO() {
    }

    public LiabilitasLainnyaResponseDTO(LiabilitasLainnya liabilitasLainnya) {
        kodeCoa = liabilitasLainnya.getKodeCoa();
        keterangan = liabilitasLainnya.getKeterangan();
        jenisLiabilitas = liabilitasLainnya.getJenisLiabilitas();
        golonganKreditur = liabilitasLainnya.getGolonganKreditur();
        hubunganKreditur = liabilitasLainnya.getHubunganKreditur();
        negaraKreditur = liabilitasLainnya.getNegaraKreditur();
        pendBknPend = liabilitasLainnya.getPendBknPend();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenisLiabilitas() {
        return jenisLiabilitas;
    }

    public void setJenisLiabilitas(String jenisLiabilitas) {
        this.jenisLiabilitas = jenisLiabilitas;
    }

    public String getGolonganKreditur() {
        return golonganKreditur;
    }

    public void setGolonganKreditur(String golonganKreditur) {
        this.golonganKreditur = golonganKreditur;
    }

    public String getHubunganKreditur() {
        return hubunganKreditur;
    }

    public void setHubunganKreditur(String hubunganKreditur) {
        this.hubunganKreditur = hubunganKreditur;
    }

    public String getNegaraKreditur() {
        return negaraKreditur;
    }

    public void setNegaraKreditur(String negaraKreditur) {
        this.negaraKreditur = negaraKreditur;
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend;
    }
}
