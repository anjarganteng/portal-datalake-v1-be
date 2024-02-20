package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * @author satiya
 */
public class ApplicationRolesRequestDTO implements Serializable {

    private static final long serialVersionUID = -2595925782516361503L;

    private String uuidRoles;
    private String nameRoles;
    private List<ApplicationRolesMenusRequestDTO> menuList;

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

    public List<ApplicationRolesMenusRequestDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<ApplicationRolesMenusRequestDTO> menuList) {
        this.menuList = menuList;
    }
}
