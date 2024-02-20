package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.CoaBot;

/**
 *
 * @author radit
 */
public class CoaBotResponseDTO {
    
    private static final long serialVersionUID = 1L;
    
    private String glCode;
    private String currency;
    private String glName;
    private String glCodeKbank;
    private String accountName;
    private String productCode;
    private String reporting;
    private String botReport;

    public CoaBotResponseDTO() {
    }

    public CoaBotResponseDTO(CoaBot coaBot) {
        this.glCode = coaBot.getEmbedCoaBot().getGlCode();
        this.currency = coaBot.getEmbedCoaBot().getCurrency();
        this.glName = coaBot.getGlName();
        this.glCodeKbank = coaBot.getGlCodeKbank();
        this.accountName = coaBot.getAccountName();
        this.productCode = coaBot.getProductCode();
        this.reporting = coaBot.getReporting();
        this.botReport = coaBot.getBotReport();
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getGlName() {
        return glName;
    }

    public void setGlName(String glName) {
        this.glName = glName;
    }

    public String getGlCodeKbank() {
        return glCodeKbank;
    }

    public void setGlCodeKbank(String glCodeKbank) {
        this.glCodeKbank = glCodeKbank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReporting() {
        return reporting;
    }

    public void setReporting(String reporting) {
        this.reporting = reporting;
    }

    public String getBotReport() {
        return botReport;
    }

    public void setBotReport(String botReport) {
        this.botReport = botReport;
    }
    
}
