package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.CifDikecualikanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelcifdikecualikan")
public class CifDikecualikan {

    @Id
    @Column(name = "no_cif")
    private String noCif;

    @Column(name = "flag")
    private String flag;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "flag_dn_ln")
    private String flagDnLn;

    public CifDikecualikan() {
    }

    public CifDikecualikan(CifDikecualikanRequestDTO cifDikecualikanRequestDTO) {
        noCif = cifDikecualikanRequestDTO.getNoCif();
        flag = cifDikecualikanRequestDTO.getFlag();
        keterangan = cifDikecualikanRequestDTO.getKeterangan();
        flagDnLn = cifDikecualikanRequestDTO.getFlagDnLn();
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
