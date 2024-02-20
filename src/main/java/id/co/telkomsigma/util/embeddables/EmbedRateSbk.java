package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.CounterRateSbkRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedRateSbk implements Serializable {

    @Column(name = "JENIS_PENGGUNAAN")
    private String jenisPenggunaan;

    @Column(name = "JENIS_VALUTA")
    private String jenisValuta;

    public EmbedRateSbk() {
    }

    public EmbedRateSbk(CounterRateSbkRequestDTO reqEmbedDTO) {
        jenisPenggunaan = reqEmbedDTO.getJenisPenggunaan();
        jenisValuta = reqEmbedDTO.getJenisValuta();
    }

    public String getJenisPenggunaan() {
        return jenisPenggunaan;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }
}
