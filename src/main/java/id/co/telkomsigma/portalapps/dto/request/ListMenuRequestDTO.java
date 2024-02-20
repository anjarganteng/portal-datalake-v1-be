package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ListMenuRequestDTO implements Serializable {

    private static final long serialVersionUID = -329560853191514289L;

    private Long id;
    private String namaMenu;
    private String deskripsi;
    private String namaTabel;
    private String linkTabel;
    private Long parentId;
    private boolean isTable;
    private boolean isActive;
    private String pathBackend;
    private String form;

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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaTabel() {
        return namaTabel;
    }

    public void setNamaTabel(String namaTabel) {
        this.namaTabel = namaTabel;
    }

    public String getLinkTabel() {
        return linkTabel;
    }

    public void setLinkTabel(String linkTabel) {
        this.linkTabel = linkTabel;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public boolean isTable() {
        return isTable;
    }

    public void setTable(boolean table) {
        isTable = table;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPathBackend() {
        return pathBackend;
    }

    public void setPathBackend(String pathBackend) {
        this.pathBackend = pathBackend;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
