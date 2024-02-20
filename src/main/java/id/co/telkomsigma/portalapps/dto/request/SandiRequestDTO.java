package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class SandiRequestDTO implements Serializable {

    private static final long serialVersionUID = -4124803659654404315L;

    private String tipeSandi;
    private String sandi;
    private String label1;
    private String label2;

    public String getTipeSandi() {
        return tipeSandi;
    }

    public void setTipeSandi(String tipeSandi) {
        this.tipeSandi = tipeSandi;
    }

    public String getSandi() {
        return sandi;
    }

    public void setSandi(String sandi) {
        this.sandi = sandi;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }
}
