package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.AtbRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedAtb implements Serializable {

    @Column(name = "jenis_aset")
    private String jenisAset;

    @Column(name = "tgl_perolehan")
    private String tglPerolehan;

    @Column(name = "tgl_mulai")
    private String tglMulai;

    public EmbedAtb() {
    }

    public EmbedAtb(AtbRequestDTO reqEmbedDTO) {
        jenisAset = reqEmbedDTO.getJenisAset();
        tglPerolehan = reqEmbedDTO.getTglPerolehan();
        tglMulai = reqEmbedDTO.getTglMulai();
    }

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset;
    }

    public String getTglPerolehan() {
        return tglPerolehan;
    }

    public void setTglPerolehan(String tglPerolehan) {
        this.tglPerolehan = tglPerolehan;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }
}
