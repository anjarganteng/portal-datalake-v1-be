package id.co.telkomsigma.portalapps.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_investment_net_bot")
public class InvestmentNet {

    @Id
    @Column(name = "description")
    private String description;
    
    @Column(name = "revaluation_surplus")
    private BigDecimal revaluationSurplus;
    
    @Column(name = "revaluation_deficit")
    private BigDecimal revaluationDeficit;
    
    @Column(name = "expected_credit_loss")
    private BigDecimal expectedCreditLoss;
    
    @Column(name = "hedge_investment")
    private BigDecimal hedgeInvestment;
    
    @Column(name = "dividend_income")
    private BigDecimal dividendIncome;

    public InvestmentNet() {
    }

    public InvestmentNet(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRevaluationSurplus() {
        return revaluationSurplus;
    }

    public void setRevaluationSurplus(BigDecimal revaluationSurplus) {
        this.revaluationSurplus = revaluationSurplus;
    }

    public BigDecimal getRevaluationDeficit() {
        return revaluationDeficit;
    }

    public void setRevaluationDeficit(BigDecimal revaluationDeficit) {
        this.revaluationDeficit = revaluationDeficit;
    }

    public BigDecimal getExpectedCreditLoss() {
        return expectedCreditLoss;
    }

    public void setExpectedCreditLoss(BigDecimal expectedCreditLoss) {
        this.expectedCreditLoss = expectedCreditLoss;
    }

    public BigDecimal getHedgeInvestment() {
        return hedgeInvestment;
    }

    public void setHedgeInvestment(BigDecimal hedgeInvestment) {
        this.hedgeInvestment = hedgeInvestment;
    }

    public BigDecimal getDividendIncome() {
        return dividendIncome;
    }

    public void setDividendIncome(BigDecimal dividendIncome) {
        this.dividendIncome = dividendIncome;
    }
    
}
