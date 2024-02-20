package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CabangPelaporRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcabangpelapor")
public class CabangPelapor {

    @Column(name = "status_data")
    private String statusData;

    @Id
    @Column(name = "kode_cabang")
    private String kodeCabang;

    @Column(name = "kode_cabang_utama")
    private String kodeCabangUtama;

    @Column(name = "kode_coa")
    private String kodeCoa;

    @Column(name = "nama_cabang")
    private String namaCabang;

    @Column(name = "alias_cabang")
    private String aliasCabang;

    @Column(name = "alias_wilayah")
    private String aliasWilayah;

    @Column(name = "kode_cab_bi")
    private String kodeCabBi;

    public CabangPelapor() {
    }

    public CabangPelapor(CabangPelaporRequestDTO cabangPelaporRequestDTO) {
        statusData = cabangPelaporRequestDTO.getStatusData();
        kodeCabang = cabangPelaporRequestDTO.getKodeCabang();
        kodeCabangUtama = cabangPelaporRequestDTO.getKodeCabangUtama();
        kodeCoa = cabangPelaporRequestDTO.getKodeCoa();
        namaCabang = cabangPelaporRequestDTO.getNamaCabang();
        aliasCabang = cabangPelaporRequestDTO.getAliasCabang();
        aliasWilayah = cabangPelaporRequestDTO.getAliasWilayah();
        kodeCabBi = cabangPelaporRequestDTO.getKodeCabBi();
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
