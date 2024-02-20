package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.InvestmentNet;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class InvestmentNetResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String description;
    private BigDecimal revaluationSurplus;
    private BigDecimal revaluationDeficit;
    private BigDecimal expectedCreditLoss;
    private BigDecimal hedgeInvestment;
    private BigDecimal dividendIncome;    

    public InvestmentNetResponseDTO() {
    }

    public InvestmentNetResponseDTO(InvestmentNet investmentNet) {
        this.description = investmentNet.getDescription();
        this.revaluationSurplus = investmentNet.getRevaluationSurplus();
        this.revaluationDeficit = investmentNet.getRevaluationDeficit();
        this.expectedCreditLoss = investmentNet.getExpectedCreditLoss();
        this.hedgeInvestment = investmentNet.getHedgeInvestment();
        this.dividendIncome = investmentNet.getDividendIncome();
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
