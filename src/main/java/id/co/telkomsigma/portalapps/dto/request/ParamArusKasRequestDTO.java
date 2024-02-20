package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ParamArusKasRequestDTO implements Serializable {

    private static final long serialVersionUID = -3394765066566141120L;

    private String sandiReferensi;
    private String label;
    private boolean flagKbu;
    private boolean flagRpp;
    private boolean isNew;

    public String getSandiReferensi() {
        return sandiReferensi;
    }

    public void setSandiReferensi(String sandiReferensi) {
        this.sandiReferensi = sandiReferensi.toUpperCase();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isFlagKbu() {
        return flagKbu;
    }

    public void setFlagKbu(boolean flagKbu) {
        this.flagKbu = flagKbu;
    }

    public boolean isFlagRpp() {
        return flagRpp;
    }

    public void setFlagRpp(boolean flagRpp) {
        this.flagRpp = flagRpp;
    }
}
