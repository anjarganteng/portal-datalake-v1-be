package id.co.telkomsigma.monitorantasena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

/**
 * @author satiya
 */
@Entity
@Table(name = "TabelProsesUmum")
public class ProsesUmum {

    @Id
    @Column(name = "nama_proses")
    private String namaProses;

    @Column(name = "tgl_proses")
    private Date tglProses;

    @Column(name = "waktu_mulai")
    private Time waktuProses;

    @Column(name = "waktu_selesai")
    private Time waktuSelesai;

    @Column(name = "diproses")
    private Integer diproses;

    @Column(name = "total")
    private Integer total;

    @Column(name = "status_data")
    private String statusData;

    @Column(name = "status_proses")
    private String statusProses;

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

    public Time getWaktuProses() {
        return waktuProses;
    }

    public void setWaktuProses(Time waktuProses) {
        this.waktuProses = waktuProses;
    }

    public Time getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(Time waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
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

    public String getStatusProses() {
        return statusProses;
    }

    public void setStatusProses(String statusProses) {
        this.statusProses = statusProses;
    }
}
