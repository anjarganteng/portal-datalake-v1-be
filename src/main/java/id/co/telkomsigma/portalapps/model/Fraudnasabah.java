package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.FraudnasabahRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelfraudnasabah")
public class Fraudnasabah {

    @Id
    @Column(name = "NO_CIF")
    private String noCif;

    public Fraudnasabah() {
    }

    public Fraudnasabah(FraudnasabahRequestDTO fraudnasabahRequestDTO) {
        noCif = fraudnasabahRequestDTO.getNoCif();
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }
}
