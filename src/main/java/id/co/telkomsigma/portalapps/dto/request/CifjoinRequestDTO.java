package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

public class CifjoinRequestDTO implements Serializable {

    private static final long serialVersionUID = 1532054372581659047L;

    private String flagNasabah;
    private String tipeNasabah;
    private String noIdentitas;
    private String cif;
    private String cifJoin;
    private String tipeJoin;
    private boolean isNew;

    public String getFlagNasabah() {
        return flagNasabah;
    }

    public void setFlagNasabah(String flagNasabah) {
        this.flagNasabah = flagNasabah.toUpperCase();
    }

    public String getTipeNasabah() {
        return tipeNasabah;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah.toUpperCase();
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas.toUpperCase();
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif.toUpperCase();
    }

    public String getCifJoin() {
        return cifJoin;
    }

    public void setCifJoin(String cifJoin) {
        this.cifJoin = cifJoin.toUpperCase();
    }

    public String getTipeJoin() {
        return tipeJoin;
    }

    public void setTipeJoin(String tipeJoin) {
        this.tipeJoin = tipeJoin.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
