package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.LiabilitasAntarKantor;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class LiabilitasAntarKantorResponseDTO {

    private static final long serialVersionUID = 3348017159217611719L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String usahaKantor;
    private String statusKantor;
    private String negaraKantor;
    private String jenisLiabilitas;
    private String pendBknPend;
    private BigDecimal sukuBungaRp;
    private BigDecimal sukuBungaVl;

    public LiabilitasAntarKantorResponseDTO() {
    }

    public LiabilitasAntarKantorResponseDTO(LiabilitasAntarKantor liabilitasAntarKantor) {
        kodeCoa = liabilitasAntarKantor.getKodeCoa();
        deskripsiCoa = liabilitasAntarKantor.getDeskripsiCoa();
        usahaKantor = liabilitasAntarKantor.getUsahaKantor();
        statusKantor = liabilitasAntarKantor.getStatusKantor();
        negaraKantor = liabilitasAntarKantor.getNegaraKantor();
        jenisLiabilitas = liabilitasAntarKantor.getJenisLiabilitas();
        pendBknPend = liabilitasAntarKantor.getPendBknPend();
        sukuBungaRp = liabilitasAntarKantor.getSukuBungaRp();
        sukuBungaVl = liabilitasAntarKantor.getSukuBungaVl();
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
