package id.co.telkomsigma.portalapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelprosesgenerate")
public class ProsesGenerate {

    @Id
    @Column(name = "nomor")
    private Long nomor;

    @Column(name = "nama_proses")
    private String namaProses;

    @Column(name = "status_data")
    private String statusData;

    @Column(name = "status_proses")
    private String statusProses;

    public Long getNomor() {
        return nomor;
    }

    public void setNomor(Long nomor) {
        this.nomor = nomor;
    }

    public String getNamaProses() {
        return namaProses;
    }

    public void setNamaProses(String namaProses) {
        this.namaProses = namaProses;
    }

    public String getStatusData() {
        return statusData;
    }

    public void setStatusData(String statusData) {
        this.statusData = statusData;
    }

    public String getStatusProses() {
        return statusProses;
    }

    public void setStatusProses(String statusProses) {
        this.statusProses = statusProses;
    }
}
