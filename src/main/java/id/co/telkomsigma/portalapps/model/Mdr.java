package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.MdrRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelmdr")
public class Mdr {

    @Id
    @Column(name = "kode_merchant")
    private String kodeMerchant;

    @Column(name = "rate")
    private BigDecimal rate;

    public Mdr() {
    }

    public Mdr(MdrRequestDTO mdrRequestDTO) {
        kodeMerchant = mdrRequestDTO.getKodeMerchant();
        rate = mdrRequestDTO.getRate();
    }

    public String getKodeMerchant() {
        return kodeMerchant;
    }

    public void setKodeMerchant(String kodeMerchant) {
        this.kodeMerchant = kodeMerchant;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
