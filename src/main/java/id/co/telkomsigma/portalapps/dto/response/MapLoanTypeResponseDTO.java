package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.MapLoanType;

/**
 *
 * @author radit
 */
public class MapLoanTypeResponseDTO {
    private static final long serialVersionUID = 1L;
    private String loanType;
    private String bmiProduct;
    private String kbankProduct;

    public MapLoanTypeResponseDTO() {
    }

    public MapLoanTypeResponseDTO(MapLoanType mapLoanType) {
        this.loanType = mapLoanType.getEmbedMapLoanType().getLoanType();
        this.bmiProduct = mapLoanType.getEmbedMapLoanType().getBmiProduct();
        this.kbankProduct = mapLoanType.getKbankProduct();
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getBmiProduct() {
        return bmiProduct;
    }

    public void setBmiProduct(String bmiProduct) {
        this.bmiProduct = bmiProduct;
    }

    public String getKbankProduct() {
        return kbankProduct;
    }

    public void setKbankProduct(String kbankProduct) {
        this.kbankProduct = kbankProduct;
    }
    
}
