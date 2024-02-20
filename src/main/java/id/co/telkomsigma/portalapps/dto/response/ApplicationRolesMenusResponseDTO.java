package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ApplicationRolesMenus;

/**
 * @author satiya
 */
public class ApplicationRolesMenusResponseDTO extends BaseModelDTO {

    private static final long serialVersionUID = 4881436268459228051L;

    private Long rolesMenusId;
    private Long menuId;
    private boolean canRead;
    private boolean canUpdate;
    private boolean canDownload;
    private boolean canUpload;
    private boolean canRegenerate;
    private boolean isIsAdmin;

    public ApplicationRolesMenusResponseDTO() {
    }

    public ApplicationRolesMenusResponseDTO(ApplicationRolesMenus applicationRolesMenus) {
        rolesMenusId = applicationRolesMenus.getRolesMenusId();
        menuId = applicationRolesMenus.getListMenu().getId();
        canRead = applicationRolesMenus.isCanRead();
        canUpdate = applicationRolesMenus.isCanUpdate();
        canDownload = applicationRolesMenus.isCanDownload();
        canUpload = applicationRolesMenus.isCanUpload();
        canRegenerate = applicationRolesMenus.isCanRegenerate();

        baseInfo(applicationRolesMenus);
    }

    public Long getRolesMenusId() {
        return rolesMenusId;
    }

    public void setRolesMenusId(Long rolesMenusId) {
        this.rolesMenusId = rolesMenusId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public boolean isCanDownload() {
        return canDownload;
    }

    public void setCanDownload(boolean canDownload) {
        this.canDownload = canDownload;
    }

    public boolean isCanUpload() {
        return canUpload;
    }

    public void setCanUpload(boolean canUpload) {
        this.canUpload = canUpload;
    }

    public boolean isCanRegenerate() {
        return canRegenerate;
    }

    public void setCanRegenerate(boolean canRegenerate) {
        this.canRegenerate = canRegenerate;
    }

    public boolean isIsAdmin() {
        return isIsAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        isIsAdmin = isAdmin;
    }
}
