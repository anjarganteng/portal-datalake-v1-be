package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.ParamArusKasRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabel_parameter_proyeksi_arus_kas")
public class ParamArusKas {

    @Id
    @Column(name = "sandi_referensi")
    private String sandiReferensi;

    @Column(name = "label")
    private String label;

    @Column(name = "flag_kbu")
    private boolean flagKbu;

    @Column(name = "flag_rpp")
    private boolean flagRpp;

    public ParamArusKas() {
    }

    public ParamArusKas(ParamArusKasRequestDTO paramArusKasRequestDTO) {
        sandiReferensi = paramArusKasRequestDTO.getSandiReferensi();
        label = paramArusKasRequestDTO.getLabel();
        flagKbu = paramArusKasRequestDTO.isFlagKbu();
        flagRpp = paramArusKasRequestDTO.isFlagRpp();
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
