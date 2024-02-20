package id.co.telkomsigma.portalapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelsandi")
public class Sandi {

    @Column(name = "TIPE_SANDI")
    private String tipeSandi;

    @Id
    @Column(name = "SANDI")
    private String sandi;

    @Column(name = "LABEL_1")
    private String label1;

    @Column(name = "LABEL_2")
    private String label2;

    public String getTipeSandi() {
        return tipeSandi;
    }

    public void setTipeSandi(String tipeSandi) {
        this.tipeSandi = tipeSandi;
    }

    public String getSandi() {
        return sandi;
    }

    public void setSandi(String sandi) {
        this.sandi = sandi;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }
}
