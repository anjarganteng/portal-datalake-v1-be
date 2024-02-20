package id.co.telkomsigma.portalapps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedDeferredTax implements Serializable {
    @Column(name = "code")
    private String code;
    @Column(name = "group_code")
    private String groupCode;

    public EmbedDeferredTax() {
    }

    public EmbedDeferredTax(String code, String groupCode) {
        this.code = code;
        this.groupCode = groupCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    
}
