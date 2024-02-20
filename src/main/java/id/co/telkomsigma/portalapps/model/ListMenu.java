package id.co.telkomsigma.portalapps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.telkomsigma.portalapps.dto.request.ListMenuRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.Select2ResponseDTO;

import javax.persistence.*;
import java.util.Set;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabellistmenu_new")
public class ListMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama_menu")
    private String namaMenu;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "nama_tabel")
    private String namaTabel;

    @Column(name = "link_tabel")
    private String linkTable;

    @OneToMany(mappedBy = "parentId")
    private Set<ListMenu> childrens;

    @JsonIgnore
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "is_table")
    private boolean isIsTable;

    @Column(name = "is_active")
    private boolean isIsActive;

    @Column(name = "path_backend")
    private String pathBackend;

    @Column(name = "form")
    private String form;

    @JsonIgnore
    @OneToMany(mappedBy = "listMenu")
    private Set<ApplicationRolesMenus> rolesMenus;

    public ListMenu() {
    }

    public ListMenu(ListMenuRequestDTO listMenuRequestDTO) {
        id = listMenuRequestDTO.getId();
        namaMenu = listMenuRequestDTO.getNamaMenu();
        deskripsi = listMenuRequestDTO.getDeskripsi();
        namaTabel = listMenuRequestDTO.getNamaTabel();
        linkTable = listMenuRequestDTO.getLinkTabel();
        isIsTable = listMenuRequestDTO.isTable();
        isIsActive = listMenuRequestDTO.isActive();
        pathBackend = listMenuRequestDTO.getPathBackend();
        parentId = listMenuRequestDTO.getParentId();
        form = listMenuRequestDTO.getForm();
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

    public String getLinkTable() {
        return linkTable;
    }

    public void setLinkTable(String linkTable) {
        this.linkTable = linkTable;
    }

    public Set<ListMenu> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<ListMenu> childrens) {
        this.childrens = childrens;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Set<ApplicationRolesMenus> getRolesMenus() {
        return rolesMenus;
    }

    public void setRolesMenus(Set<ApplicationRolesMenus> rolesMenus) {
        this.rolesMenus = rolesMenus;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Select2ResponseDTO toSelect2ResponseDTO() {
        return new Select2ResponseDTO(this.id.toString(), this.namaMenu, false);
    }
}
