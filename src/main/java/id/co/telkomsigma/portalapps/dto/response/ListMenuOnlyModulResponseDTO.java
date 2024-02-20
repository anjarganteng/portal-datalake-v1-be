package id.co.telkomsigma.portalapps.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.co.telkomsigma.portalapps.model.ListMenu;

/**
 * @author satiya
 */
public class ListMenuOnlyModulResponseDTO {

    private static final long serialVersionUID = 5076505040716992545L;

    private Long id;
    private String namaMenu;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ApplicationRolesMenusResponseDTO permissionList;

    public ListMenuOnlyModulResponseDTO() {
    }

    public ListMenuOnlyModulResponseDTO(ListMenu listMenu) {
        id = listMenu.getId();
        namaMenu = listMenu.getNamaMenu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public ApplicationRolesMenusResponseDTO getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(ApplicationRolesMenusResponseDTO permissionList) {
        this.permissionList = permissionList;
    }
}
