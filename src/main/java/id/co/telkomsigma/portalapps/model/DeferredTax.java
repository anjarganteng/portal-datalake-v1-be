package id.co.telkomsigma.portalapps.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_deferred_tax_bot")
public class DeferredTax {
    
    @EmbeddedId
    protected EmbedDeferredTax embedDeferredTax;
    @Column(name = "description")
    private String description;
    @Column(name = "deferred_tax_assets")
    private BigDecimal deferredTaxAssets;
    @Column(name = "deferred_tax_liabilities")
    private BigDecimal deferredTaxLiabilities;

    public DeferredTax() {
    }

    public DeferredTax(EmbedDeferredTax embedDeferredTax) {
        this.embedDeferredTax = embedDeferredTax;
    }

    public DeferredTax(String code, String groupCode) {
        this.embedDeferredTax = new EmbedDeferredTax(code, groupCode);
    }

    public EmbedDeferredTax getEmbedDeferredTax() {
        return embedDeferredTax;
    }

    public void setEmbedDeferredTax(EmbedDeferredTax embedDeferredTax) {
        this.embedDeferredTax = embedDeferredTax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDeferredTaxAssets() {
        return deferredTaxAssets;
    }

    public void setDeferredTaxAssets(BigDecimal deferredTaxAssets) {
        this.deferredTaxAssets = deferredTaxAssets;
    }

    public BigDecimal getDeferredTaxLiabilities() {
        return deferredTaxLiabilities;
    }

    public void setDeferredTaxLiabilities(BigDecimal deferredTaxLiabilities) {
        this.deferredTaxLiabilities = deferredTaxLiabilities;
    }
    
}
