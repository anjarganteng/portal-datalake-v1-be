package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.PpaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedPpa implements Serializable {

    @Column(name = "sandi_jenis")
    private String sandiJenis;

    @Column(name = "tgl_mulai")
    private String tglMulai;

    @Column(name = "cabang")
    private String cabang;

    @Column(name = "wilayah")
    private String wilayah;

    @Column(name = "mata_uang")
    private String mataUang;

    public EmbedPpa() {
    }

    public EmbedPpa(PpaRequestDTO ppaRequestDTO) {
        sandiJenis = ppaRequestDTO.getSandiJenis();
        cabang = ppaRequestDTO.getCabang();
        wilayah = ppaRequestDTO.getWilayah();
        mataUang = ppaRequestDTO.getMataUang();
        tglMulai = ppaRequestDTO.getTglMulai();
    }

    public String getSandiJenis() {
        return sandiJenis;
    }

    public void setSandiJenis(String sandiJenis) {
        this.sandiJenis = sandiJenis;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }
}

