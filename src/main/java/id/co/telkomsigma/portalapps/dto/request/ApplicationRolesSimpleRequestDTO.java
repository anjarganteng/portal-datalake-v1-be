package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

public class ApplicationRolesSimpleRequestDTO implements Serializable {

    private static final long serialVersionUID = 2557892751062268252L;

    private String uuidRoles;
    private String nameRoles;

    public String getUuidRoles() {
        return uuidRoles;
    }

    public void setUuidRoles(String uuidRoles) {
        this.uuidRoles = uuidRoles;
    }

    public String getNameRoles() {
        return nameRoles;
    }

    public void setNameRoles(String nameRoles) {
        this.nameRoles = nameRoles;
    }
}
