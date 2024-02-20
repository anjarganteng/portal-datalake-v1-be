package id.co.telkomsigma.util.embeddables;

import id.co.telkomsigma.portalapps.dto.request.CoaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmbedCoa implements Serializable {

    @Column(name = "coa_induk")
    private String coaInduk;

    @Column(name = "coa_detail")
    private String coaDetail;

    public EmbedCoa() {
    }

    public EmbedCoa(CoaRequestDTO coaRequestDTO) {
        coaInduk = coaRequestDTO.getCoaInduk();
        coaDetail = coaRequestDTO.getCoaDetail();
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
