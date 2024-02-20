package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CounterRateSbsRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedRateSbs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcounterratesbs")
public class CounterRateSbs {

    @EmbeddedId
    private EmbedRateSbs embedRateSbs;

    @Column(name = "COUNTER_RATE_MIN")
    private BigDecimal rateMin;

    @Column(name = "COUNTER_RATE_MAX")
    private BigDecimal rateMax;

    public CounterRateSbs() {
    }

    public CounterRateSbs(EmbedRateSbs embedRateSbs, CounterRateSbsRequestDTO counterRateSbsRequestDTO) {
        this.embedRateSbs = embedRateSbs;
        rateMin = counterRateSbsRequestDTO.getRateMin();
        rateMax = counterRateSbsRequestDTO.getRateMax();
    }

    public EmbedRateSbs getEmbedRateSbs() {
        return embedRateSbs;
    }

    public void setEmbedRateSbs(EmbedRateSbs embedRateSbs) {
        this.embedRateSbs = embedRateSbs;
    }

    public BigDecimal getRateMin() {
        return rateMin;
    }

    public void setRateMin(BigDecimal rateMin) {
        this.rateMin = rateMin;
    }

    public BigDecimal getRateMax() {
        return rateMax;
    }

    public void setRateMax(BigDecimal rateMax) {
        this.rateMax = rateMax;
    }
}
