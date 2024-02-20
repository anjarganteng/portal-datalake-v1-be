package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedCifToFi implements Serializable {
    @Column(name = "cif_no")
    private String cifNo;
    @Column(name = "flag")
    private String flag;

    public EmbedCifToFi() {
    }

    public EmbedCifToFi(String cifNo, String flag) {
        this.cifNo = cifNo;
        this.flag = flag;
    }

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
}