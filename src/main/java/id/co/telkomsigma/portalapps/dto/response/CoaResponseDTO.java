package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Coa;

/**
 * @author satiya
 */
public class CoaResponseDTO {

    private static final long serialVersionUID = 853162538301594505L;

    private String coaInduk;
    private String coaDetail;

    public CoaResponseDTO() {
    }

    public CoaResponseDTO(Coa coa) {
        coaInduk = coa.getEmbedCoa().getCoaInduk();
        coaDetail = coa.getEmbedCoa().getCoaDetail();
    }

    public String getCoaInduk() {
        return coaInduk;
    }

    public void setCoaInduk(String coaInduk) {
        this.coaInduk = coaInduk;
    }

    public String getCoaDetail() {
        return coaDetail;
    }

    public void setCoaDetail(String coaDetail) {
        this.coaDetail = coaDetail;
    }
}
