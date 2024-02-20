package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.LpsRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedLps implements Serializable {

    @Column(name = "tanggal_mulai")
    private String tglMulai;

    @Column(name = "jenis_valuta")
    private String jenisValuta;

    public EmbedLps() {
    }

    public EmbedLps(LpsRequestDTO reqEmbedDTO) {
        tglMulai = reqEmbedDTO.getTglMulai();
        jenisValuta = reqEmbedDTO.getJenisValuta();
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }
}

