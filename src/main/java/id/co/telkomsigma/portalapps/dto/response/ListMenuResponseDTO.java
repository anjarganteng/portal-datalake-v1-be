package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ListMenu;

import java.util.Set;

/**
 * @author satiya
 */
public class ListMenuResponseDTO {

    private static final long serialVersionUID = 7482799770689229571L;

    private Long id;
    private String namaMenu;
    private String deskripsi;
    private String namaTabel;
    private String linkTabel;
    private boolean isIsTable;
    private boolean isIsActive;
    private String pathBackend;
    private String form;

    private Set<ListMenu> childrens;

    public ListMenuResponseDTO() {
    }

    public ListMenuResponseDTO(ListMenu listMenu) {
        id = listMenu.getId();
        namaMenu = listMenu.getNamaMenu();
        deskripsi = listMenu.getDeskripsi();
        namaTabel = listMenu.getNamaTabel();
        linkTabel = listMenu.getLinkTable();
        isIsTable = listMenu.isIsTable();
        isIsActive = listMenu.isIsTable();
        pathBackend = listMenu.getPathBackend();
        form = listMenu.getForm();
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

    public boolean isIsTable() {
        return isIsTable;
    }

    public void setIsTable(boolean isTable) {
        isIsTable = isTable;
    }

    public boolean isIsActive() {
        return isIsActive;
    }

    public void setIsActive(boolean isActive) {
        isIsActive = isActive;
    }

    public String getPathBackend() {
        return pathBackend;
    }

    public void setPathBackend(String pathBackend) {
        this.pathBackend = pathBackend;
    }

    public Set<ListMenu> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<ListMenu> childrens) {
        this.childrens = childrens;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
