package id.co.telkomsigma.portalapps.dto.request;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class AsetAntarKantorRequestDTO implements Serializable {

    private static final long serialVersionUID = -8589225739518241397L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String usahaKantor;
    private String statusKantor;
    private String negaraKantor;
    private String jenisAset;
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

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset.toUpperCase();
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
