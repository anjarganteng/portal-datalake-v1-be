package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedCoaBot;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_coa_bot")
public class CoaBot {
    @EmbeddedId
    protected EmbedCoaBot embedCoaBot;

    @Column(name = "gl_name")  
    private String glName;

    @Column(name = "gl_code_kbank")
    private String glCodeKbank;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "reporting")
    private String reporting;
    
    @Column(name = "bot_report")
    private String botReport;

    public CoaBot() {
    }

    public CoaBot(EmbedCoaBot embedCoaBot, String glName, String glCodeKbank, String accountName, String productCode, String reporting, String botReport) {
        this.embedCoaBot = embedCoaBot;
        this.glName = glName;
        this.glCodeKbank = glCodeKbank;
        this.accountName = accountName;
        this.productCode = productCode;
        this.reporting = reporting;
        this.botReport = botReport;
    }
    
    public CoaBot(EmbedCoaBot embedCoaBot) {
        this.embedCoaBot = embedCoaBot;
    }

    public CoaBot(String glCode, String currency) {
        this.embedCoaBot = new EmbedCoaBot(glCode, currency);
    }

    public EmbedCoaBot getEmbedCoaBot() {
        return embedCoaBot;
    }

    public void setEmbedCoaBot(EmbedCoaBot embedCoaBot) {
        this.embedCoaBot = embedCoaBot;
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
