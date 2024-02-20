package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedKpmm implements Serializable {
    
    @Column(name = "kode_form")
    private String kodeForm;
    
    @Column(name = "kode_komponen")
    private String kodeKomponen;

    public EmbedKpmm() {
    }

    public EmbedKpmm(String kodeForm, String kodeKomponen) {
        this.kodeForm = kodeForm;
        this.kodeKomponen = kodeKomponen;
    }

    public String getKodeForm() {
        return kodeForm;
    }

    public void setKodeForm(String kodeForm) {
        this.kodeForm = kodeForm;
    }

    public String getKodeKomponen() {
        return kodeKomponen;
    }

    public void setKodeKomponen(String kodeKomponen) {
        this.kodeKomponen = kodeKomponen;
    }
    
}
