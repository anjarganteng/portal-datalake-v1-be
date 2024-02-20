package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class SetoranJaminanRequestDTO implements Serializable {

    private static final long serialVersionUID = -1598089795519848072L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String tujuanSetoranJaminan;
    private String golonganPemilik;
    private String hubDgnPelapor;
    private String pendBknPend;
    private boolean isNew;

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa.toUpperCase();
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa.toUpperCase();
    }

    public String getTujuanSetoranJaminan() {
        return tujuanSetoranJaminan;
    }

    public void setTujuanSetoranJaminan(String tujuanSetoranJaminan) {
        this.tujuanSetoranJaminan = tujuanSetoranJaminan.toUpperCase();
    }

    public String getGolonganPemilik() {
        return golonganPemilik;
    }

    public void setGolonganPemilik(String golonganPemilik) {
        this.golonganPemilik = golonganPemilik.toUpperCase();
    }

    public String getHubDgnPelapor() {
        return hubDgnPelapor;
    }

    public void setHubDgnPelapor(String hubDgnPelapor) {
        this.hubDgnPelapor = hubDgnPelapor.toUpperCase();
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
