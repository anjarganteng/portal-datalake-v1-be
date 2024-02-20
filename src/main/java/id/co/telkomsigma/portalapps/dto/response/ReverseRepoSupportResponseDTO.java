package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ReverseRepoSupport;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author radit
 */
public class ReverseRepoSupportResponseDTO {
    
    private static final long serialVersionUID = 1L;

    private String underlyingNo;
    private Date publicationDate;
    private Date dueDate;
    private BigDecimal interestRate;
    private String couponType;
    private String interestPaymentFrequency;
    private String type;
    private BigDecimal amount;
    private BigDecimal rounding;
    private BigDecimal secondLeg;
    private BigDecimal cadanganPendapatan;
    private BigDecimal outstanding;

    public ReverseRepoSupportResponseDTO() {
    }

    public ReverseRepoSupportResponseDTO(ReverseRepoSupport reverseRepoSupport) {
        this.underlyingNo = reverseRepoSupport.getUnderlyingNo();
        this.publicationDate = reverseRepoSupport.getPublicationDate();
        this.dueDate = reverseRepoSupport.getDueDate();
        this.interestRate = reverseRepoSupport.getInterestRate();
        this.couponType = reverseRepoSupport.getCouponType();
        this.interestPaymentFrequency = reverseRepoSupport.getInterestPaymentFrequency();
        this.type = reverseRepoSupport.getType();
        this.amount = reverseRepoSupport.getAmount();
        this.rounding = reverseRepoSupport.getRounding();
        this.secondLeg = reverseRepoSupport.getSecondLeg();
        this.cadanganPendapatan = reverseRepoSupport.getCadanganPendapatan();
        this.outstanding = reverseRepoSupport.getOutstanding();
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
