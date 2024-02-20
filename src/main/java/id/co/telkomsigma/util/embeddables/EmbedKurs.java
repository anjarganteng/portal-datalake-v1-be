package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.KursRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedKurs implements Serializable {

    @Column(name = "mata_uang")
    private String mataUang;

    @Column(name = "tanggal")
    private String tanggal;

    @Column(name = "jenis")
    private String jenis;

    public EmbedKurs() {
    }

    public EmbedKurs(KursRequestDTO reqEmbedDTO) {
        mataUang = reqEmbedDTO.getMataUang();
        tanggal = reqEmbedDTO.getTanggal().replace("-", "");
        jenis = reqEmbedDTO.getJenis();
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
