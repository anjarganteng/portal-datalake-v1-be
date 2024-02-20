package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.CifDikecualikan;

/**
 * @author satiya
 */
public class CifDikecualikanResponseDTO {

    private static final long serialVersionUID = 4350384964846856392L;

    public String noCif;
    public String flag;
    public String keterangan;
    public String flagDnLn;

    public CifDikecualikanResponseDTO() {
    }

    public CifDikecualikanResponseDTO(CifDikecualikan cifDikecualikan) {
        noCif = cifDikecualikan.getNoCif();
        flag = cifDikecualikan.getFlag();
        keterangan = cifDikecualikan.getKeterangan();
        flagDnLn = cifDikecualikan.getFlagDnLn();
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagDnLn() {
        return flagDnLn;
    }

    public void setFlagDnLn(String flagDnLn) {
        this.flagDnLn = flagDnLn;
    }
}
