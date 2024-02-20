package id.co.telkomsigma.portalapps.dto.response;

import java.io.Serializable;

/**
 * @author satiya
 */
public class Select2ResponseDTO implements Serializable {

    private static final long serialVersionUID = 4584817404289510155L;

    private String id;

    private String text;

    private boolean selected;

    public Select2ResponseDTO() {
        super();
    }

    public Select2ResponseDTO(String id, String text, boolean selected) {
        super();
        this.id = id;
        this.text = text;
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
