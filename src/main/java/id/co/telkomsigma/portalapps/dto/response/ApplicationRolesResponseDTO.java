package id.co.telkomsigma.portalapps.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.co.telkomsigma.portalapps.model.ApplicationRoles;

import java.util.List;

/**
 * @author satiya
 */
public class ApplicationRolesResponseDTO extends BaseModelDTO {

    private static final long serialVersionUID = 6520954511123605251L;

    private String uuidRoles;
    private String nameRoles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApplicationRolesMenusResponseDTO> menuList;

    public ApplicationRolesResponseDTO() {
    }

    public ApplicationRolesResponseDTO(ApplicationRoles applicationRoles) {
        uuidRoles = applicationRoles.getUuidRoles().toString();
        nameRoles = applicationRoles.getNameRoles();

        baseInfo(applicationRoles);
    }

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

    public List<ApplicationRolesMenusResponseDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<ApplicationRolesMenusResponseDTO> menuList) {
        this.menuList = menuList;
    }
}
