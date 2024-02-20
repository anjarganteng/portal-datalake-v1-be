package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.SetoranJaminan;

/**
 * @author satiya
 */
public class SetoranJaminanResponseDTO {

    private static final long serialVersionUID = 7539027935354099094L;

    private String kodeCoa;
    private String deskripsiCoa;
    private String tujuanSetoranJaminan;
    private String golonganPemilik;
    private String hubDgnPelapor;
    private String pendBknPend;

    public SetoranJaminanResponseDTO() {
    }

    public SetoranJaminanResponseDTO(SetoranJaminan setoranJaminan) {
        kodeCoa = setoranJaminan.getKodeCoa();
        deskripsiCoa = setoranJaminan.getDeskripsiCoa();
        tujuanSetoranJaminan = setoranJaminan.getTujuanSetoranJaminan();
        golonganPemilik = setoranJaminan.getGolonganPemilik();
        hubDgnPelapor = setoranJaminan.getHubDgnPelapor();
        pendBknPend = setoranJaminan.getPendBknPend();
    }

    public String getKodeCoa() {
        return kodeCoa;
    }

    public void setKodeCoa(String kodeCoa) {
        this.kodeCoa = kodeCoa;
    }

    public String getDeskripsiCoa() {
        return deskripsiCoa;
    }

    public void setDeskripsiCoa(String deskripsiCoa) {
        this.deskripsiCoa = deskripsiCoa;
    }

    public String getTujuanSetoranJaminan() {
        return tujuanSetoranJaminan;
    }

    public void setTujuanSetoranJaminan(String tujuanSetoranJaminan) {
        this.tujuanSetoranJaminan = tujuanSetoranJaminan;
    }

    public String getGolonganPemilik() {
        return golonganPemilik;
    }

    public void setGolonganPemilik(String golonganPemilik) {
        this.golonganPemilik = golonganPemilik;
    }

    public String getHubDgnPelapor() {
        return hubDgnPelapor;
    }

    public void setHubDgnPelapor(String hubDgnPelapor) {
        this.hubDgnPelapor = hubDgnPelapor;
    }

    public String getPendBknPend() {
        return pendBknPend;
    }

    public void setPendBknPend(String pendBknPend) {
        this.pendBknPend = pendBknPend;
    }
}
