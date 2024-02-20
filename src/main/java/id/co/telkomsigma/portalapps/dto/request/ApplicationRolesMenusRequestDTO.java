package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ApplicationRolesMenusRequestDTO implements Serializable {

    private static final long serialVersionUID = -5540946611024208569L;

    private Long rolesMenusId;
    private Long menuId;
    private boolean canRead;
    private boolean canUpdate;
    private boolean canDownload;
    private boolean canUpload;
    private boolean canRegenerate;
    private boolean isIsAdmin;

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
