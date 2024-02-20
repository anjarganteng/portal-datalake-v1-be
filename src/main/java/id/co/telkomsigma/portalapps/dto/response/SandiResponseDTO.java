package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Sandi;

/**
 * @author satiya
 */
public class SandiResponseDTO {

    private static final long serialVersionUID = -4901766492165164488L;

    private String tipeSandi;
    private String sandi;
    private String label1;
    private String label2;

    public SandiResponseDTO() {
    }

    public SandiResponseDTO(Sandi response) {
        tipeSandi = response.getTipeSandi();
        sandi = response.getSandi();
        label1 = response.getLabel1();
        label2 = response.getLabel2();

    }

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
