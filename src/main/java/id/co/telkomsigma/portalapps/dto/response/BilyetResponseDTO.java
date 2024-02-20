package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Bilyet;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author radit
 */
public class BilyetResponseDTO {
    private static final long serialVersionUID = 1L;
    
    private String checkNo;
    private String checkType;
    private String purpose;
    private Date issuedDate;
    private Date expiredDate;
    private String currency;
    private BigDecimal documentsAmount;
    private String applicantCustomerNo;

    public BilyetResponseDTO() {
    }

    public BilyetResponseDTO(Bilyet bilyet) {
        this.checkNo = bilyet.getCheckNo();
        this.checkType = bilyet.getCheckType();
        this.purpose = bilyet.getPurpose();
        this.issuedDate = bilyet.getIssuedDate();
        this.expiredDate = bilyet.getExpiredDate();
        this.currency = bilyet.getCurrency();
        this.documentsAmount = bilyet.getDocumentsAmount();
        this.applicantCustomerNo = bilyet.getApplicantCustomerNo();
    }

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
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
