package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.AsetAntarKantorRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelaak")
public class AsetAntarKantor {

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

    @Column(name = "jenis_aset")
    private String jenisAset;

    @Column(name = "suku_bunga_rp")
    private BigDecimal sukuBungaRp;

    @Column(name = "suku_bunga_vl")
    private BigDecimal sukuBungaVl;

    public AsetAntarKantor() {
    }

    public AsetAntarKantor(AsetAntarKantorRequestDTO asetAntarKantorRequestDTO) {
        kodeCoa = asetAntarKantorRequestDTO.getKodeCoa();
        deskripsiCoa = asetAntarKantorRequestDTO.getDeskripsiCoa();
        usahaKantor = asetAntarKantorRequestDTO.getUsahaKantor();
        statusKantor = asetAntarKantorRequestDTO.getStatusKantor();
        negaraKantor = asetAntarKantorRequestDTO.getNegaraKantor();
        jenisAset = asetAntarKantorRequestDTO.getJenisAset();
        sukuBungaRp = asetAntarKantorRequestDTO.getSukuBungaRp();
        sukuBungaVl = asetAntarKantorRequestDTO.getSukuBungaVl();
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
