package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class CifjoinqqRequestDTO implements Serializable {

    private static final long serialVersionUID = -443528662695018947L;

    private String cifQq;
    private String namaLengkap;
    private boolean isNew;

    public String getCifQq() {
        return cifQq;
    }

    public void setCifQq(String cifQq) {
        this.cifQq = cifQq.toUpperCase();
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
