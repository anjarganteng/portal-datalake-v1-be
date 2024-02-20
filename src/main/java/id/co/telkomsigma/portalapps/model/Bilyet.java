package id.co.telkomsigma.portalapps.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author radit
 */
@Entity
@Table(name = "tabel_bilyet_bot")
public class Bilyet {

    @Column(name = "check_no")
    private String checkNo;

    @Column(name = "check_type")
    private String checkType;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "issued_date")
    private Date issuedDate;

    @Column(name = "expired_date")
    private Date expiredDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "documents_amount")
    private BigDecimal documentsAmount;

    @Id
    @Column(name = "applicant_customer_no")
    private String applicantCustomerNo;

    public Bilyet() {
    }

    public Bilyet(String applicantCustomerNo) {
        this.applicantCustomerNo = applicantCustomerNo;
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
