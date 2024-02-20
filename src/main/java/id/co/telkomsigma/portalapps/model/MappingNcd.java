package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.MappingNcdRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelmappingncd")
public class MappingNcd {

    @Id
    @Column(name = "no_deposito")
    private String noDeposito;

    @Column(name = "no_cif")
    private String noCif;

    public MappingNcd() {
    }

    public MappingNcd(MappingNcdRequestDTO mappingNcdRequestDTO) {
        noDeposito = mappingNcdRequestDTO.getNoDeposito();
        noCif = mappingNcdRequestDTO.getNoCif();
    }

    public String getNoDeposito() {
        return noDeposito;
    }

    public void setNoDeposito(String noDeposito) {
        this.noDeposito = noDeposito;
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }
}
