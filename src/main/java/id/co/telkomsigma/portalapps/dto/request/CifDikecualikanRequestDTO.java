package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class CifDikecualikanRequestDTO implements Serializable {

    private static final long serialVersionUID = -70889793973153534L;

    public String noCif;
    public String flag;
    public String keterangan;
    public String flagDnLn;
    private boolean isNew;

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif.toUpperCase();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag.toUpperCase();
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan.toUpperCase();
    }

    public String getFlagDnLn() {
        return flagDnLn;
    }

    public void setFlagDnLn(String flagDnLn) {
        this.flagDnLn = flagDnLn.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
