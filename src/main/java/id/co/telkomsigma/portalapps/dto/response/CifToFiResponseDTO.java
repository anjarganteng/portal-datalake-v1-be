package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.CifToFi;
import javax.persistence.Column;

/**
 *
 * @author radit
 */
public class CifToFiResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String cifNo;
    private String flag;

    public CifToFiResponseDTO() {
    }

    public CifToFiResponseDTO(CifToFi cifToFi) {
        this.cifNo = cifToFi.getEmbedCifToFi().getCifNo();
        this.flag = cifToFi.getEmbedCifToFi().getFlag();
    }
    
    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    
    
}
