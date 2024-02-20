package id.co.telkomsigma.portalapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit
 */

@Entity
@Table(name = "tabel_funding_concentration_interbank_bot")
public class FundingConcentrationInterbank {

    @Id
    @Column(name = "cif_no")
    private String cifNo;

    public FundingConcentrationInterbank() {
    }

    public FundingConcentrationInterbank(String cifNo) {
        this.cifNo = cifNo;
    }

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }
    
}
