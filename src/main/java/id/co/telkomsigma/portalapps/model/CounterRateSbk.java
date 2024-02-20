package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CounterRateSbkRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedRateSbk;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcounterratesbk")
public class CounterRateSbk {

    @EmbeddedId
    private EmbedRateSbk embedRateSbk;

    @Column(name = "COUNTER_RATE_FLAT")
    private BigDecimal rateFlat;

    @Column(name = "COUNTER_RATE_EFEKTIF")
    private BigDecimal rateEfektif;

    public CounterRateSbk() {
    }

    public CounterRateSbk(EmbedRateSbk embedRateSbk, CounterRateSbkRequestDTO counterRateSbkRequestDTO) {
        this.embedRateSbk = embedRateSbk;
        rateFlat = counterRateSbkRequestDTO.getRateFlat();
        rateEfektif = counterRateSbkRequestDTO.getRateEfektif();
    }

    public EmbedRateSbk getEmbedRateSbk() {
        return embedRateSbk;
    }

    public void setEmbedRateSbk(EmbedRateSbk embedRateSbk) {
        this.embedRateSbk = embedRateSbk;
    }

    public BigDecimal getRateFlat() {
        return rateFlat;
    }

    public void setRateFlat(BigDecimal rateFlat) {
        this.rateFlat = rateFlat;
    }

    public BigDecimal getRateEfektif() {
        return rateEfektif;
    }

    public void setRateEfektif(BigDecimal rateEfektif) {
        this.rateEfektif = rateEfektif;
    }
}
