package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ApplicationPermissionRequestDTO implements Serializable {

    public static final long serialVersionUID = -6860587888975548166L;

    private String uuidPermission;
    private String namePermission;

    public String getUuidPermission() {
        return uuidPermission;
    }

    public void setUuidPermission(String uuidPermission) {
        this.uuidPermission = uuidPermission;
    }

    public String getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(String namePermission) {
        this.namePermission = namePermission;
    }
}
