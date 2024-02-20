package id.co.telkomsigma.monitorantasena.model;

import id.co.telkomsigma.monitorantasena.dto.ProsesManualRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author satiya
 */
@Entity
@Table(name = "TabelProsesManual")
public class ProsesManual {

    @Id
    @Column(name = "nama_proses")
    private String namaProses;

    @Column(name = "tgl_proses")
    private Date tglProses;

    @Column(name = "diproses")
    private Integer diproses;

    @Column(name = "total")
    private Integer total;

    @Column(name = "status_data")
    private String statusData;

    public ProsesManual() {
    }

    public ProsesManual(ProsesManualRequestDTO prosesManualRequestDTO) {
        tglProses = Date.valueOf(prosesManualRequestDTO.getTglProses());
        statusData = prosesManualRequestDTO.getStatusData();
    }

    public String getNamaProses() {
        return namaProses;
    }

    public void setNamaProses(String namaProses) {
        this.namaProses = namaProses;
    }

    public Date getTglProses() {
        return tglProses;
    }

    public void setTglProses(Date tglProses) {
        this.tglProses = tglProses;
    }

    public Integer getDiproses() {
        return diproses;
    }

    public void setDiproses(Integer diproses) {
        this.diproses = diproses;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatusData() {
        return statusData;
    }

    public void setStatusData(String statusData) {
        this.statusData = statusData;
    }
}
