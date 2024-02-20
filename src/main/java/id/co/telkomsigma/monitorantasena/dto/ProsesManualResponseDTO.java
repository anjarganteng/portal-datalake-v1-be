package id.co.telkomsigma.monitorantasena.dto;

import id.co.telkomsigma.monitorantasena.model.ProsesManual;

/**
 * @author satiya
 */
public class ProsesManualResponseDTO {

    private static final long serialVersionUID = 260074769306702726L;

    private String namaProses;
    private String tglProses;
    private String statusData;

    public ProsesManualResponseDTO() {
    }

    public ProsesManualResponseDTO(ProsesManual prosesManual) {
        namaProses = prosesManual.getNamaProses();
        tglProses = String.valueOf(prosesManual.getTglProses());
        statusData = prosesManual.getStatusData();
    }

    public String getNamaProses() {
        return namaProses;
    }

    public void setNamaProses(String namaProses) {
        this.namaProses = namaProses;
    }

    public String getTglProses() {
        return tglProses;
    }

    public void setTglProses(String tglProses) {
        this.tglProses = tglProses;
    }

    public String getStatusData() {
        return statusData;
    }

    public void setStatusData(String statusData) {
        this.statusData = statusData;
    }
}
