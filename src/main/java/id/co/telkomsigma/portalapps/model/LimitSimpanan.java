package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.LimitSimpanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabellimitsimpanandijamin")
public class LimitSimpanan {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nominal_dijamin")
    private BigDecimal nominalDijamin;

    public LimitSimpanan() {
    }

    public LimitSimpanan(LimitSimpanRequestDTO limitSimpanRequestDTO) {
        id = limitSimpanRequestDTO.getId();
        nominalDijamin = limitSimpanRequestDTO.getNominalDijamin();
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
