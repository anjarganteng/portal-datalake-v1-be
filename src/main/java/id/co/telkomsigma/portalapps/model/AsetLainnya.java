package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.AsetLainnyaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelasl")
public class AsetLainnya {

    @Id
    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "keterangan_jenis_lainnya")
    private String keteranganJenisLainnya;

    @Column(name = "jenis_aset")
    private String jenisAset;

    @Column(name = "golongan_debitur")
    private String golonganDebitur;

    @Column(name = "hubungan_debitur")
    private String hubunganDebitur;

    @Column(name = "pend_bkn_pend")
    private String pendBknPend;

    public AsetLainnya() {
    }

    public AsetLainnya(AsetLainnyaRequestDTO asetLainnyaRequestDTO) {
        kodeCoa = asetLainnyaRequestDTO.getKodeCoa();
        keteranganJenisLainnya = asetLainnyaRequestDTO.getKeteranganJenisLainnya();
        jenisAset = asetLainnyaRequestDTO.getJenisAset();
        golonganDebitur = asetLainnyaRequestDTO.getGolonganDebitur();
        hubunganDebitur = asetLainnyaRequestDTO.getHubunganDebitur();
        pendBknPend = asetLainnyaRequestDTO.getPendBknPend();
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
