package id.co.telkomsigma.portalapps.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.co.telkomsigma.portalapps.dto.request.ApplicationUsersRequestDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author satiya
 */
@Entity
@Table(name = "application_users")
public class ApplicationUsers extends BaseModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid_users", columnDefinition = "BINARY(16)")
    private UUID uuidUsers;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_roles", nullable = false)
    private ApplicationRoles applicationRoles;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "application_users_branch",
            joinColumns = {@JoinColumn(name = "uuid_users")},
            inverseJoinColumns = {@JoinColumn(name = "uuid_branch")})
    private Set<ApplicationBranch> branches;

    public ApplicationUsers() {
    }

    public ApplicationUsers(ApplicationUsersRequestDTO applicationUsersRequestDTO) {
        uuidUsers = applicationUsersRequestDTO.getUuidUsers() != null ? UUID.fromString(applicationUsersRequestDTO.getUuidUsers()) : null;
        email = applicationUsersRequestDTO.getEmail();
        fullname = applicationUsersRequestDTO.getFullname();
        phone = applicationUsersRequestDTO.getPhone();
        password = applicationUsersRequestDTO.getPassword();
        isActive = applicationUsersRequestDTO.isActive();
    }

    public UUID getUuidUsers() {
        return uuidUsers;
    }

    public void setUuidUsers(UUID uuidUsers) {
        this.uuidUsers = uuidUsers;
    }

    public ApplicationRoles getApplicationRoles() {
        return applicationRoles;
    }

    public void setApplicationRoles(ApplicationRoles applicationRoles) {
        this.applicationRoles = applicationRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<ApplicationBranch> getBranches() {
        return branches;
    }

    public void setBranches(Set<ApplicationBranch> branches) {
        this.branches = branches;
    }
}
