package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.LpsRequestDTO;
import id.co.telkomsigma.util.embeddables.EmbedLps;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelbungalps")
public class Lps {

    @EmbeddedId
    private EmbedLps embedLps;

    @Column(name = "tanggal_jt")
    private String tglJt;

    @Column(name = "suku_bunga")
    private BigDecimal sukuBunga;

    public Lps() {
    }

    public Lps(EmbedLps embedLps, LpsRequestDTO lpsRequestDTO) {
        this.embedLps = embedLps;
        tglJt = lpsRequestDTO.getTglJt();
        sukuBunga = lpsRequestDTO.getSukuBunga();
    }

    public EmbedLps getEmbedLps() {
        return embedLps;
    }

    public void setEmbedLps(EmbedLps embedLps) {
        this.embedLps = embedLps;
    }

    public String getTglJt() {
        return tglJt;
    }

    public void setTglJt(String tglJt) {
        this.tglJt = tglJt;
    }

    public BigDecimal getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(BigDecimal sukuBunga) {
        this.sukuBunga = sukuBunga;
    }
}

