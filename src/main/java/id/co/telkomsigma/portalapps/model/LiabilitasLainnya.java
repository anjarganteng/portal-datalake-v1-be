package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.LiabilitasLainnyaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabellil")
public class LiabilitasLainnya {

    @Id
    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "jenis_liabilitas")
    private String jenisLiabilitas;

    @Column(name = "gol_kreditur")
    private String golonganKreditur;

    @Column(name = "hub_kreditur")
    private String hubunganKreditur;

    @Column(name = "negara_kreditur")
    private String negaraKreditur;

    @Column(name = "pend_bkn_pend")
    private String pendBknPend;

    public LiabilitasLainnya() {
    }

    public LiabilitasLainnya(LiabilitasLainnyaRequestDTO liabilitasLainnyaRequestDTO) {
        kodeCoa = liabilitasLainnyaRequestDTO.getKodeCoa();
        keterangan = liabilitasLainnyaRequestDTO.getKeterangan();
        jenisLiabilitas = liabilitasLainnyaRequestDTO.getJenisLiabilitas();
        golonganKreditur = liabilitasLainnyaRequestDTO.getGolonganKreditur();
        hubunganKreditur = liabilitasLainnyaRequestDTO.getHubunganKreditur();
        negaraKreditur = liabilitasLainnyaRequestDTO.getNegaraKreditur();
        pendBknPend = liabilitasLainnyaRequestDTO.getPendBknPend();
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
