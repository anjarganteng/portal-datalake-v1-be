package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class Select2RequestSearchDTO implements Serializable {

    private static final long serialVersionUID = 1992698694512716799L;

    private String text;

    public Select2RequestSearchDTO() {
        super();
    }

    public Select2RequestSearchDTO(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
