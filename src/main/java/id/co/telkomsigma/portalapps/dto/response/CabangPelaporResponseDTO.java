package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.CabangPelapor;

/**
 * @author satiya
 */
public class CabangPelaporResponseDTO {

    private static final long serialVersionUID = 60597658435120716L;

    private String statusData;
    private String kodeCabang;
    private String kodeCabangUtama;
    private String kodeCoa;
    private String namaCabang;
    private String aliasCabang;
    private String aliasWilayah;
    private String kodeCabBi;

    public CabangPelaporResponseDTO() {
    }

    public CabangPelaporResponseDTO(CabangPelapor cabangPelapor) {
        statusData = cabangPelapor.getStatusData();
        kodeCabang = cabangPelapor.getKodeCabang();
        kodeCabangUtama = cabangPelapor.getKodeCabangUtama();
        kodeCoa = cabangPelapor.getKodeCoa();
        namaCabang = cabangPelapor.getNamaCabang();
        aliasCabang = cabangPelapor.getAliasCabang();
        aliasWilayah = cabangPelapor.getAliasWilayah();
        kodeCabBi = cabangPelapor.getKodeCabBi();
    }

    public String getStatusData() {
        return statusData;
    }

    public void setStatusData(String statusData) {
        this.statusData = statusData;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getKodeCabangUtama() {
        return kodeCabangUtama;
    }

    public void setKodeCabangUtama(String kodeCabangUtama) {
        this.kodeCabangUtama = kodeCabangUtama;
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getAliasCabang() {
        return aliasCabang;
    }

    public void setAliasCabang(String aliasCabang) {
        this.aliasCabang = aliasCabang;
    }

    public String getAliasWilayah() {
        return aliasWilayah;
    }

    public void setAliasWilayah(String aliasWilayah) {
        this.aliasWilayah = aliasWilayah;
    }

    public String getKodeCabBi() {
        return kodeCabBi;
    }

    public void setKodeCabBi(String kodeCabBi) {
        this.kodeCabBi = kodeCabBi;
    }
}
