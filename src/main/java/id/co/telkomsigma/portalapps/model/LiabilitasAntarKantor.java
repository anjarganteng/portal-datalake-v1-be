package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.LiabilitasAntarKantorRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabellak")
public class LiabilitasAntarKantor {

    @Id
    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "deskripsi_coa")
    private String deskripsiCoa;

    @Column(name = "usaha_kantor")
    private String usahaKantor;

    @Column(name = "status_kantor")
    private String statusKantor;

    @Column(name = "negara_kantor")
    private String negaraKantor;

    @Column(name = "jenis_liabilitas")
    private String jenisLiabilitas;

    @Column(name = "pend_bkn_pend")
    private String pendBknPend;

    @Column(name = "suku_bunga_rp")
    private BigDecimal sukuBungaRp;

    @Column(name = "suku_bunga_vl")
    private BigDecimal sukuBungaVl;

    public LiabilitasAntarKantor() {
    }

    public LiabilitasAntarKantor(LiabilitasAntarKantorRequestDTO liabilitasAntarKantorRequestDTO) {
        kodeCoa = liabilitasAntarKantorRequestDTO.getKodeCoa();
        deskripsiCoa = liabilitasAntarKantorRequestDTO.getDeskripsiCoa();
        usahaKantor = liabilitasAntarKantorRequestDTO.getUsahaKantor();
        statusKantor = liabilitasAntarKantorRequestDTO.getStatusKantor();
        negaraKantor = liabilitasAntarKantorRequestDTO.getNegaraKantor();
        jenisLiabilitas = liabilitasAntarKantorRequestDTO.getJenisLiabilitas();
        pendBknPend = liabilitasAntarKantorRequestDTO.getPendBknPend();
        sukuBungaRp = liabilitasAntarKantorRequestDTO.getSukuBungaRp();
        sukuBungaVl = liabilitasAntarKantorRequestDTO.getSukuBungaVl();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa;
    }

    public String getUsahaKantor() {
        return usahaKantor;
    }

    public void setUsahaKantor(String usahaKantor) {
        this.usahaKantor = usahaKantor;
    }

    public String getStatusKantor() {
        return statusKantor;
    }

    public void setStatusKantor(String statusKantor) {
        this.statusKantor = statusKantor;
    }

    public String getNegaraKantor() {
        return negaraKantor;
    }

    public void setNegaraKantor(String negaraKantor) {
        this.negaraKantor = negaraKantor;
    }

    public String getJenisLiabilitas() {
        return jenisLiabilitas;
    }

    public void setJenisLiabilitas(String jenisLiabilitas) {
        this.jenisLiabilitas = jenisLiabilitas;
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend;
    }

    public BigDecimal getSukuBungaRp() {
        return sukuBungaRp;
    }

    public void setSukuBungaRp(BigDecimal sukuBungaRp) {
        this.sukuBungaRp = sukuBungaRp;
    }

    public BigDecimal getSukuBungaVl() {
        return sukuBungaVl;
    }

    public void setSukuBungaVl(BigDecimal sukuBungaVl) {
        this.sukuBungaVl = sukuBungaVl;
    }
}
