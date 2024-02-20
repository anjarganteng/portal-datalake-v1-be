package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ParamArusKas;

/**
 * @author satiya
 */
public class ParamArusKasResponseDTO {

    private static final long serialVersionUID = 7153820294855264135L;

    private String sandiReferensi;
    private boolean flagKbu;
    private boolean flagRpp;
    private String label;

    public ParamArusKasResponseDTO() {
    }

    public ParamArusKasResponseDTO(ParamArusKas paramArusKas) {
        sandiReferensi = paramArusKas.getSandiReferensi();
        label = paramArusKas.getLabel();
        flagKbu = paramArusKas.isFlagKbu();
        flagRpp = paramArusKas.isFlagRpp();
    }

    public String getSandiReferensi() {
        return sandiReferensi;
    }

    public void setSandiReferensi(String sandiReferensi) {
        this.sandiReferensi = sandiReferensi;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
