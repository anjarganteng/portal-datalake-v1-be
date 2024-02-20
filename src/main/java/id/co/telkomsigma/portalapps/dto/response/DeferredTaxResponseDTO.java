package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.DeferredTax;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class DeferredTaxResponseDTO {
    private static final long serialVersionUID = 1L;
    private String code;
    private String groupCode;
    private String description;
    private BigDecimal deferredTaxAssets;
    private BigDecimal deferredTaxLiabilities;

    public DeferredTaxResponseDTO() {
    }

    public DeferredTaxResponseDTO(DeferredTax deferredTax) {
        this.code = deferredTax.getEmbedDeferredTax().getCode();
        this.groupCode = deferredTax.getEmbedDeferredTax().getGroupCode();
        this.description = deferredTax.getDescription();
        this.deferredTaxAssets = deferredTax.getDeferredTaxAssets();
        this.deferredTaxLiabilities = deferredTax.getDeferredTaxLiabilities();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
