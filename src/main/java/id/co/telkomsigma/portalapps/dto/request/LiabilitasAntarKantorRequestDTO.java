package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class LiabilitasAntarKantorRequestDTO implements Serializable {

    private static final long serialVersionUID = 4138094939327357722L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String usahaKantor;
    private String statusKantor;
    private String negaraKantor;
    private String jenisLiabilitas;
    private String pendBknPend;
    private BigDecimal sukuBungaRp;
    private BigDecimal sukuBungaVl;
    private boolean isNew;

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa.toUpperCase();
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa.toUpperCase();
    }

    public String getUsahaKantor() {
        return usahaKantor;
    }

    public void setUsahaKantor(String usahaKantor) {
        this.usahaKantor = usahaKantor.toUpperCase();
    }

    public String getStatusKantor() {
        return statusKantor;
    }

    public void setStatusKantor(String statusKantor) {
        this.statusKantor = statusKantor.toUpperCase();
    }

    public String getNegaraKantor() {
        return negaraKantor;
    }

    public void setNegaraKantor(String negaraKantor) {
        this.negaraKantor = negaraKantor.toUpperCase();
    }

    public String getJenisLiabilitas() {
        return jenisLiabilitas;
    }

    public void setJenisLiabilitas(String jenisLiabilitas) {
        this.jenisLiabilitas = jenisLiabilitas.toUpperCase();
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend.toUpperCase();
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
