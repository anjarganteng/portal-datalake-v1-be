package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Securities;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class SecuritiesResponseDTO {

    private static final long serialVersionUID = 1L;
    
    private String uniqueId;
    private BigDecimal finalAmount;
    private BigDecimal buyAmount;
    private BigDecimal marketToMarket;

    public SecuritiesResponseDTO() {
    }

    public SecuritiesResponseDTO(Securities securities) {
        this.uniqueId = securities.getUniqueId();
        this.finalAmount = securities.getFinalAmount();
        this.buyAmount = securities.getBuyAmount();
        this.marketToMarket = securities.getMarketToMarket();
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
