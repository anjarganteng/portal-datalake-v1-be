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
@Table(name = "tabel_wesel_bot")
public class Wesel {
    @Column(name = "no")
    private int no;

    @Column(name = "reference")
    private String reference;

    @Column(name = "skbdn_type")
    private String skbdnType;

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

    public Wesel() {
    }

    public Wesel(int no, String reference, String skbdnType, String purpose, Date issuedDate, Date expiredDate, String currency, BigDecimal documentsAmount, String applicantCustomerNo) {
        this.no = no;
        this.reference = reference;
        this.skbdnType = skbdnType;
        this.purpose = purpose;
        this.issuedDate = issuedDate;
        this.expiredDate = expiredDate;
        this.currency = currency;
        this.documentsAmount = documentsAmount;
        this.applicantCustomerNo = applicantCustomerNo;
    }

    public Wesel(String applicantCustomerNo) {
        this.applicantCustomerNo = applicantCustomerNo;
    }

    public Wesel(String applicantCustomerNo, int no) {
        this.applicantCustomerNo = applicantCustomerNo;
        this.no = no;
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
