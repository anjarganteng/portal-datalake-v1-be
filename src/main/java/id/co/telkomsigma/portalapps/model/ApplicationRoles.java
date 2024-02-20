package id.co.telkomsigma.portalapps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesSimpleRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.Select2ResponseDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author satiya
 */
@Entity
@Table(name = "application_roles")
public class ApplicationRoles extends BaseModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid_roles", columnDefinition = "BINARY(16)")
    private UUID uuidRoles;

    @Column(name = "name_roles")
    private String nameRoles;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationRoles")
    private Set<ApplicationUsers> applicationUsers;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationRoles", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ApplicationRolesMenus> roleMenus;

    public ApplicationRoles() {
    }

    public ApplicationRoles(ApplicationRolesRequestDTO applicationRolesRequestDTO) {
        uuidRoles = applicationRolesRequestDTO.getUuidRoles() != null ? UUID.fromString(applicationRolesRequestDTO.getUuidRoles()) : null;
        nameRoles = applicationRolesRequestDTO.getNameRoles();
    }

    public ApplicationRoles(ApplicationRolesSimpleRequestDTO applicationRolesSimpleRequestDTO) {
        uuidRoles = applicationRolesSimpleRequestDTO.getUuidRoles() != null ? UUID.fromString(applicationRolesSimpleRequestDTO.getUuidRoles()) : null;
        nameRoles = applicationRolesSimpleRequestDTO.getNameRoles();
    }

    public UUID getUuidRoles() {
        return uuidRoles;
    }

    public void setUuidRoles(UUID uuidRoles) {
        this.uuidRoles = uuidRoles;
    }

    public String getNameRoles() {
        return nameRoles;
    }

    public void setNameRoles(String nameRoles) {
        this.nameRoles = nameRoles;
    }

    public Set<ApplicationRolesMenus> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Set<ApplicationRolesMenus> roleMenus) {
        this.roleMenus = roleMenus;
    }

    public Select2ResponseDTO toSelect2ResponseDTO() {
        return new Select2ResponseDTO(this.uuidRoles.toString(), this.nameRoles, false);
    }

}
