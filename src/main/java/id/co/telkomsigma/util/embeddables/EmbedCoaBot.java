package id.co.telkomsigma.util.embeddables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author radit
 */
@Embeddable
public class EmbedCoaBot implements Serializable {

    @Column(name = "gl_code")
    private String glCode;

    @Column(name = "currency")
    private String currency;

    public EmbedCoaBot() {
    }

    public EmbedCoaBot(String glCode, String currency) {
        this.glCode = glCode;
        this.currency = currency;
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
}
