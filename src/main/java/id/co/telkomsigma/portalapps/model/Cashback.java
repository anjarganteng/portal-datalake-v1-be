package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CashbackRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcashback")
public class Cashback {

    @Id
    @Column(name = "no_rek")
    private String noRek;

    @Column(name = "jns_simpanan")
    private String jnsSimpanan;

    @Column(name = "nominal_cashback")
    private BigDecimal nominalCashback;

    @Column(name = "persentase_cashback")
    private BigDecimal persentaseCashback;

    public Cashback() {
    }

    public Cashback(CashbackRequestDTO cashbackRequestDTO) {
        noRek = cashbackRequestDTO.getNoRek();
        jnsSimpanan = cashbackRequestDTO.getJnsSimpanan();
        nominalCashback = cashbackRequestDTO.getNominalCashback();
        persentaseCashback = cashbackRequestDTO.getPersentaseCashback();
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getJnsSimpanan() {
        return jnsSimpanan;
    }

    public void setJnsSimpanan(String jnsSimpanan) {
        this.jnsSimpanan = jnsSimpanan;
    }

    public BigDecimal getNominalCashback() {
        return nominalCashback;
    }

    public void setNominalCashback(BigDecimal nominalCashback) {
        this.nominalCashback = nominalCashback;
    }

    public BigDecimal getPersentaseCashback() {
        return persentaseCashback;
    }

    public void setPersentaseCashback(BigDecimal persentaseCashback) {
        this.persentaseCashback = persentaseCashback;
    }
}
