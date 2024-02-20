package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Cashback;

import java.math.BigDecimal;

/**
 * @author satiya
 */
public class CashbackResponseDTO {

    private static final long serialVersionUID = -3392395889501889985L;

    private String noRek;
    private String jnsSimpanan;
    private BigDecimal nominalCashback;
    private BigDecimal persentaseCashback;

    public CashbackResponseDTO() {
    }

    public CashbackResponseDTO(Cashback cashback) {
        noRek = cashback.getNoRek();
        jnsSimpanan = cashback.getJnsSimpanan();
        nominalCashback = cashback.getNominalCashback();
        persentaseCashback = cashback.getPersentaseCashback();

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
