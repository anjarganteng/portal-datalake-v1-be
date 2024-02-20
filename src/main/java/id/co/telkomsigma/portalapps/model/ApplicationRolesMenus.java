package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesMenusRequestDTO;

import javax.persistence.*;

/**
 * @author satiya
 */
@Entity
@Table(name = "application_roles_menus")
public class ApplicationRolesMenus extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_menus_id")
    private Long rolesMenusId;

    @ManyToOne
    @JoinColumn(name = "uuid_roles")
    private ApplicationRoles applicationRoles;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private ListMenu listMenu;

    @Column(name = "can_read")
    private boolean canRead;

    @Column(name = "can_update")
    private boolean canUpdate;

    @Column(name = "can_download")
    private boolean canDownload;

    @Column(name = "can_upload")
    private boolean canUpload;

    @Column(name = "can_regenerate")
    private boolean canRegenerate;

    @Column(name = "is_admin")
    private boolean isIsAdmin;

    public ApplicationRolesMenus() {
    }

    public ApplicationRolesMenus(ApplicationRolesMenusRequestDTO requestDTO) {
        rolesMenusId = requestDTO.getRolesMenusId() != null ? requestDTO.getRolesMenusId() : null;
        canRead = requestDTO.isCanRead();
        canUpdate = requestDTO.isCanUpdate();
        canDownload = requestDTO.isCanDownload();
        canUpload = requestDTO.isCanUpload();
        canRegenerate = requestDTO.isCanRegenerate();
        isIsAdmin = requestDTO.isIsAdmin();
    }

    public Long getRolesMenusId() {
        return rolesMenusId;
    }

    public void setRolesMenusId(Long rolesMenusId) {
        this.rolesMenusId = rolesMenusId;
    }

    public ApplicationRoles getApplicationRoles() {
        return applicationRoles;
    }

    public void setApplicationRoles(ApplicationRoles applicationRoles) {
        this.applicationRoles = applicationRoles;
    }

    public ListMenu getListMenu() {
        return listMenu;
    }

    public void setListMenu(ListMenu listMenu) {
        this.listMenu = listMenu;
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
