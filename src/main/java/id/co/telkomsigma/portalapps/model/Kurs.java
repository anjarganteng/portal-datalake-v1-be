package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.KursRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedKurs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelkurs")
public class Kurs {

    @EmbeddedId
    private EmbedKurs embedKurs;

    @Column(name = "kurs_jual")
    private BigDecimal kursJual;

    @Column(name = "kurs_beli")
    private BigDecimal kursBeli;

    public Kurs() {
    }

    public Kurs(EmbedKurs embedKurs, KursRequestDTO kursRequestDTO) {
        this.embedKurs = embedKurs;
        kursJual = kursRequestDTO.getKursJual();
        kursBeli = kursRequestDTO.getKursBeli();
    }

    public EmbedKurs getEmbedKurs() {
        return embedKurs;
    }

    public void setEmbedKurs(EmbedKurs embedKurs) {
        this.embedKurs = embedKurs;
    }

    public BigDecimal getKursJual() {
        return kursJual;
    }

    public void setKursJual(BigDecimal kursJual) {
        this.kursJual = kursJual;
    }

    public BigDecimal getKursBeli() {
        return kursBeli;
    }

    public void setKursBeli(BigDecimal kursBeli) {
        this.kursBeli = kursBeli;
    }
}
