package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.BaseModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * @author satiya
 */
public class BaseModelDTO implements Serializable {

    private static final long serialVersionUID = -7924815158765573397L;

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss Z";

    private String createdDate;
    private String modifiedDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void baseInfo(BaseModel model) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        createdDate = sdf.format(model.getCreatedDate());
        modifiedDate = sdf.format(model.getModifiedDate());
    }

}
