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
@Table(name = "tabel_reverse_repo_support_bot")
public class ReverseRepoSupport {

    @Id
    @Column(name = "underlying_no")
    private String underlyingNo;
    
    @Column(name = "publication_date")
    private Date publicationDate;
    
    @Column(name = "due_date")
    private Date dueDate;
    
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    
    @Column(name = "coupon_type")
    private String couponType;
    
    @Column(name = "interest_payment_frequency")
    private String interestPaymentFrequency;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "rounding")
    private BigDecimal rounding;
    
    @Column(name = "second_leg")
    private BigDecimal secondLeg;
    
    @Column(name = "cadangan_pendapatan")
    private BigDecimal cadanganPendapatan;
    
    @Column(name = "outstanding")
    private BigDecimal outstanding;

    public ReverseRepoSupport() {
    }

    public ReverseRepoSupport(String underlyingNo, Date publicationDate, Date dueDate, BigDecimal interestRate, String couponType, String interestPaymentFrequency, String type, BigDecimal amount, BigDecimal rounding, BigDecimal secondLeg, BigDecimal cadanganPendapatan, BigDecimal outstanding) {
        this.underlyingNo = underlyingNo;
        this.publicationDate = publicationDate;
        this.dueDate = dueDate;
        this.interestRate = interestRate;
        this.couponType = couponType;
        this.interestPaymentFrequency = interestPaymentFrequency;
        this.type = type;
        this.amount = amount;
        this.rounding = rounding;
        this.secondLeg = secondLeg;
        this.cadanganPendapatan = cadanganPendapatan;
        this.outstanding = outstanding;
    }

    public ReverseRepoSupport(String underlyingNo) {
        this.underlyingNo = underlyingNo;
    }

    public String getUnderlyingNo() {
        return underlyingNo;
    }

    public void setUnderlyingNo(String underlyingNo) {
        this.underlyingNo = underlyingNo;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getInterestPaymentFrequency() {
        return interestPaymentFrequency;
    }

    public void setInterestPaymentFrequency(String interestPaymentFrequency) {
        this.interestPaymentFrequency = interestPaymentFrequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRounding() {
        return rounding;
    }

    public void setRounding(BigDecimal rounding) {
        this.rounding = rounding;
    }

    public BigDecimal getSecondLeg() {
        return secondLeg;
    }

    public void setSecondLeg(BigDecimal secondLeg) {
        this.secondLeg = secondLeg;
    }

    public BigDecimal getCadanganPendapatan() {
        return cadanganPendapatan;
    }

    public void setCadanganPendapatan(BigDecimal cadanganPendapatan) {
        this.cadanganPendapatan = cadanganPendapatan;
    }

    public BigDecimal getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(BigDecimal outstanding) {
        this.outstanding = outstanding;
    }
    
}
