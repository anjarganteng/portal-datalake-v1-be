package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.PbiRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedPbi implements Serializable {

    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "mata_uang")
    private String mataUang;

    @Column(name = "mulai")
    private String mulai;

    public EmbedPbi() {
    }

    public EmbedPbi(PbiRequestDTO pbiRequestDTO) {
        kodeCoa = pbiRequestDTO.getKodeCoa();
        mataUang = pbiRequestDTO.getMataUang();
        mulai = pbiRequestDTO.getMulai();
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
