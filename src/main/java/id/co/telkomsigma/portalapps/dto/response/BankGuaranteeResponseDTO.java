package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.BankGuarantee;

/**
 *
 * @author radit
 */
public class BankGuaranteeResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String customerNo;
    private String accountNo;
    private String asetClassifitcationReason;
    private String economicSector;

    public BankGuaranteeResponseDTO() {
    }

    public BankGuaranteeResponseDTO(BankGuarantee bankGuarantee) {
        this.customerNo = bankGuarantee.getEmbedBankGuarantee().getCustomerNo();
        this.accountNo = bankGuarantee.getEmbedBankGuarantee().getAccountNo();
        this.asetClassifitcationReason = bankGuarantee.getAsetClassifitcationReason();
        this.economicSector = bankGuarantee.getEconomicSector();
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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
