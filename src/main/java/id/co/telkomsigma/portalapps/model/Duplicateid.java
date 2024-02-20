package id.co.telkomsigma.portalapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelduplicateid")
public class Duplicateid {

    @Id
    @Column(name = "CIF")
    private String cif;

    @Column(name = "NO_IDENTITAS")
    private String noIdentitas;

    @Column(name = "NM_LNKP_NSB")
    private String namaLengkap;

    @Column(name = "SUMBER")
    private String sumber;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }
}
