package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.PblRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedPbl implements Serializable {

    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "mata_uang")
    private String mataUang;

    @Column(name = "mulai")
    private String mulai;

    public EmbedPbl() {
    }

    public EmbedPbl(PblRequestDTO pblRequestDTO) {
        kodeCoa = pblRequestDTO.getKodeCoa();
        mataUang = pblRequestDTO.getMataUang();
        mulai = pblRequestDTO.getMulai();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }
}
