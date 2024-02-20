package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.CounterRateSbsRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author satiya
 */
@Embeddable
public class EmbedRateSbs implements Serializable {

    @Column(name = "JENIS_INSTRUMEN")
    private String jenisInstrumen;

    @Column(name = "JENIS_VALUTA")
    private String jenisValuta;

    @Column(name = "JANGKA_WAKTU")
    private String jangkaWaktu;

    public EmbedRateSbs() {
    }

    public EmbedRateSbs(CounterRateSbsRequestDTO reqEmbedDTO) {
        jenisInstrumen = reqEmbedDTO.getJenisInstrumen();
        jenisValuta = reqEmbedDTO.getJenisValuta();
        jangkaWaktu = reqEmbedDTO.getJangkaWaktu();
        ;
    }

    public String getJenisInstrumen() {
        return jenisInstrumen;
    }

    public void setJenisInstrumen(String jenisInstrumen) {
        this.jenisInstrumen = jenisInstrumen;
    }

    public String getJenisValuta() {
        return jenisValuta;
    }

    public void setJenisValuta(String jenisValuta) {
        this.jenisValuta = jenisValuta;
    }

    public String getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(String jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu;
    }
}
