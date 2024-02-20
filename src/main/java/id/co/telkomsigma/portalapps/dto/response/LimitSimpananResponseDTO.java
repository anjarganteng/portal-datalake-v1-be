package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.LimitSimpanan;

import java.math.BigDecimal;

public class LimitSimpananResponseDTO {

    private static final long serialVersionUID = -1500388014007038846L;

    private String id;
    private BigDecimal nominalDijamin;

    public LimitSimpananResponseDTO() {
    }

    public LimitSimpananResponseDTO(LimitSimpanan limitSimpanan) {
        id = limitSimpanan.getId();
        nominalDijamin = limitSimpanan.getNominalDijamin();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getNominalDijamin() {
        return nominalDijamin;
    }

    public void setNominalDijamin(BigDecimal nominalDijamin) {
        this.nominalDijamin = nominalDijamin;
    }
}
