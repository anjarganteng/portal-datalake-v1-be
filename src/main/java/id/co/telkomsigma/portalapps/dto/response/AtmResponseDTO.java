package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Atm;

/**
 * @author satiya
 */
public class AtmResponseDTO {

    private static final long serialVersionUID = 6926960293578694399L;

    private String id;
    private String jenis;
    private String status;
    private String lokasi;
    private String usaha;
    private String keterangan;

    public AtmResponseDTO() {
    }

    public AtmResponseDTO(Atm atm) {
        id = atm.getId();
        jenis = atm.getJenis();
        status = atm.getStatus();
        lokasi = atm.getLokasi();
        usaha = atm.getUsaha();
        keterangan = atm.getKeterangan();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getUsaha() {
        return usaha;
    }

    public void setUsaha(String usaha) {
        this.usaha = usaha;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
