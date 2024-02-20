package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class GolPihakLawanRequestDTO implements Serializable {

    private static final long serialVersionUID = 5361946419213289969L;

    private String golonganPihakLawan;
    private String golDebiturKreditur;
    private boolean isNew;

    public String getGolonganPihakLawan() {
        return golonganPihakLawan;
    }

    public void setGolonganPihakLawan(String golonganPihakLawan) {
        this.golonganPihakLawan = golonganPihakLawan.toUpperCase();
    }

    public String getGolDebiturKreditur() {
        return golDebiturKreditur;
    }

    public void setGolDebiturKreditur(String golDebiturKreditur) {
        this.golDebiturKreditur = golDebiturKreditur.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
