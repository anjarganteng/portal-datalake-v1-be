package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class CabangPelaporRequestDTO implements Serializable {

    private static final long serialVersionUID = -3013722954837297659L;

    private String statusData;
    private String kodeCabang;
    private String kodeCabangUtama;
    private String kodeCoa;
    private String namaCabang;
    private String aliasCabang;
    private String aliasWilayah;
    private String kodeCabBi;
    private boolean isNew;

    public String getStatusData() {
        return statusData;
    }

    public void setStatusData(String statusData) {
        this.statusData = statusData.toUpperCase();
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang.toUpperCase();
    }

    public String getKodeCabangUtama() {
        return kodeCabangUtama;
    }

    public void setKodeCabangUtama(String kodeCabangUtama) {
        this.kodeCabangUtama = kodeCabangUtama.toUpperCase();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa.toUpperCase();
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang.toUpperCase();
    }

    public String getAliasCabang() {
        return aliasCabang;
    }

    public void setAliasCabang(String aliasCabang) {
        this.aliasCabang = aliasCabang.toUpperCase();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getAliasWilayah() {
        return aliasWilayah;
    }

    public void setAliasWilayah(String aliasWilayah) {
        this.aliasWilayah = aliasWilayah.toUpperCase();
    }

    public String getKodeCabBi() {
        return kodeCabBi;
    }

    public void setKodeCabBi(String kodeCabBi) {
        this.kodeCabBi = kodeCabBi.toUpperCase();
    }
}
