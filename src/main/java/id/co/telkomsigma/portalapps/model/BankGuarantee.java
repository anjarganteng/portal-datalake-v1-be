package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.util.embeddables.EmbedBankGuarantee;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_bank_guarantee_bot")
public class BankGuarantee{

    @EmbeddedId
    protected EmbedBankGuarantee embedBankGuarantee;
    
    @Column(name = "aset_classifitcation_reason")
    private String asetClassifitcationReason;
    
    @Column(name = "economic_sector")
    private String economicSector;

    public BankGuarantee() {
    }

    public BankGuarantee(EmbedBankGuarantee embedBankGuarantee) {
        this.embedBankGuarantee = embedBankGuarantee;
    }

    public BankGuarantee(String customerNo, String accountNo) {
        this.embedBankGuarantee = new EmbedBankGuarantee(customerNo, accountNo);
    }

    public EmbedBankGuarantee getEmbedBankGuarantee() {
        return embedBankGuarantee;
    }

    public void setEmbedBankGuarantee(EmbedBankGuarantee embedBankGuarantee) {
        this.embedBankGuarantee = embedBankGuarantee;
    }

    public String getAsetClassifitcationReason() {
        return asetClassifitcationReason;
    }

    public void setAsetClassifitcationReason(String asetClassifitcationReason) {
        this.asetClassifitcationReason = asetClassifitcationReason;
    }

    public String getEconomicSector() {
        return economicSector;
    }

    public void setEconomicSector(String economicSector) {
        this.economicSector = economicSector;
    }
    
}
