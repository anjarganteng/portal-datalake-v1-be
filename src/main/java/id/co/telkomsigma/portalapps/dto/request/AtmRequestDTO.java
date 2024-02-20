package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class AtmRequestDTO implements Serializable {

    private static final long serialVersionUID = 8775002011730415793L;

    private String id;
    private String jenis;
    private String status;
    private String lokasi;
    private String usaha;
    private String keterangan;
    private boolean isNew;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis.toUpperCase();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi.toUpperCase();
    }

    public String getUsaha() {
        return usaha;
    }

    public void setUsaha(String usaha) {
        this.usaha = usaha.toUpperCase();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
