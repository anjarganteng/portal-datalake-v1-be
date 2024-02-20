package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.FundingConcentrationInterbank;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author radit
 */
public class FundingConcentrationInterbankResponseDTO {
    private static final long serialVersionUID = 1L;

    private String cifNo;    

    public FundingConcentrationInterbankResponseDTO() {
    }

    public FundingConcentrationInterbankResponseDTO(FundingConcentrationInterbank fundingConcentrationInterbank) {
        this.cifNo = fundingConcentrationInterbank.getCifNo();
    }

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }
    
}
