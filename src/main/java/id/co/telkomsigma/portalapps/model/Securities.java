package id.co.telkomsigma.portalapps.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_securities_bot")
public class Securities {

    @Id
    @Column(name = "unique_id")
    private String uniqueId;
    
    @Column(name = "final_amount")
    private BigDecimal finalAmount;
    
    @Column(name = "buy_amount")
    private BigDecimal buyAmount;
    
    @Column(name = "market_to_market")
    private BigDecimal marketToMarket;

    public Securities() {
    }

    public Securities(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getMarketToMarket() {
        return marketToMarket;
    }

    public void setMarketToMarket(BigDecimal marketToMarket) {
        this.marketToMarket = marketToMarket;
    }
    
}
