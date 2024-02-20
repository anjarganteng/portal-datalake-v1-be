package id.co.telkomsigma.monitorantasena.dto;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ProsesManualRequestDTO implements Serializable {

    private static final long serialVersionUID = -8008477877947352612L;

    private String namaProses;
    private String statusData;
    private String tglProses;

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

    public String getTglProses() {
        return tglProses;
    }

    public void setTglProses(String tglProses) {
        this.tglProses = tglProses;
    }
}
