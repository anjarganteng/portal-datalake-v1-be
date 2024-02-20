package id.co.telkomsigma.portalapps.model;


import id.co.telkomsigma.portalapps.dto.request.RekeningSlikRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelmappingrekbarulama")
public class RekeningSlik {

    @Id
    @Column(name = "no_rek_baru")
    private String noValid;

    @Column(name = "no_rek_lama")
    private String noSlik;

    public RekeningSlik() {
    }

    public RekeningSlik(RekeningSlikRequestDTO rekeningSlikRequestDTO) {
        noValid = rekeningSlikRequestDTO.getNoValid();
        noSlik = rekeningSlikRequestDTO.getNoSlik();
    }

    public String getNoValid() {
        return noValid;
    }

    public void setNoValid(String noValid) {
        this.noValid = noValid;
    }

    public String getNoSlik() {
        return noSlik;
    }

    public void setNoSlik(String noSlik) {
        this.noSlik = noSlik;
    }
}
