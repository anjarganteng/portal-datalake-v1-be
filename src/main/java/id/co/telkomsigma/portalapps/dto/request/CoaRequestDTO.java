package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class CoaRequestDTO implements Serializable {

    private static final long serialVersionUID = -2911293735406062269L;

    private String coaInduk;
    private String coaDetail;
    private boolean isNew;

    public String getCoaInduk() {
        return coaInduk;
    }

    public void setCoaInduk(String coaInduk) {
        this.coaInduk = coaInduk.toUpperCase();
    }

    public String getCoaDetail() {
        return coaDetail;
    }

    public void setCoaDetail(String coaDetail) {
        this.coaDetail = coaDetail.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
