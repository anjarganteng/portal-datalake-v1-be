package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Wesel;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author radit
 */
public class WeselResponseDTO {
    
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String reference;
    private String skbdnType;
    private String purpose;
    private Date issuedDate;
    private Date expiredDate;
    private String currency;
    private BigDecimal documentsAmount;
    private String applicantCustomerNo;

    public WeselResponseDTO() {
    }

    public WeselResponseDTO(Wesel wesel) {
        this.no = wesel.getNo();
        this.reference = wesel.getReference();
        this.skbdnType = wesel.getSkbdnType();
        this.purpose = wesel.getPurpose();
        this.issuedDate = wesel.getIssuedDate();
        this.expiredDate = wesel.getExpiredDate();
        this.currency = wesel.getCurrency();
        this.documentsAmount = wesel.getDocumentsAmount();
        this.applicantCustomerNo = wesel.getApplicantCustomerNo();
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSkbdnType() {
        return skbdnType;
    }

    public void setSkbdnType(String skbdnType) {
        this.skbdnType = skbdnType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getDocumentsAmount() {
        return documentsAmount;
    }

    public void setDocumentsAmount(BigDecimal documentsAmount) {
        this.documentsAmount = documentsAmount;
    }

    public String getApplicantCustomerNo() {
        return applicantCustomerNo;
    }

    public void setApplicantCustomerNo(String applicantCustomerNo) {
        this.applicantCustomerNo = applicantCustomerNo;
    }
    
    
    
}
