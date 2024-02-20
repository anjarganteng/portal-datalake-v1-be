package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.AsetAntarKantor;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class AsetAntarKantorResponseDTO {

    private static final long serialVersionUID = 1935935364322957897L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String usahaKantor;
    private String statusKantor;
    private String negaraKantor;
    private String jenisAset;
    private BigDecimal sukuBungaRp;
    private BigDecimal sukuBungaVl;

    public AsetAntarKantorResponseDTO() {
    }

    public AsetAntarKantorResponseDTO(AsetAntarKantor asetAntarKantor) {
        kodeCoa = asetAntarKantor.getKodeCoa();
        deskripsiCoa = asetAntarKantor.getDeskripsiCoa();
        usahaKantor = asetAntarKantor.getUsahaKantor();
        statusKantor = asetAntarKantor.getStatusKantor();
        negaraKantor = asetAntarKantor.getNegaraKantor();
        jenisAset = asetAntarKantor.getJenisAset();
        sukuBungaRp = asetAntarKantor.getSukuBungaRp();
        sukuBungaVl = asetAntarKantor.getSukuBungaVl();
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

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset;
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
