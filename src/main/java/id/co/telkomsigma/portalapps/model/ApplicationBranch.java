package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.ApplicationBranchRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.ApplicationBranchRequestOriginalDTO;
import id.co.telkomsigma.portalapps.dto.response.Select2ResponseDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author satiya
 */
@Entity
@Table(name = "application_branch")
public class ApplicationBranch extends BaseModel {

    private static final long serialVersionUID = 4107561894195765072L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid_branch", columnDefinition = "BINARY(16)")
    private UUID uuidBranch;

    @Column(name = "name_branch")
    private String nameBranch;

    //    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "application_users_branch",
//    joinColumns = { @JoinColumn(name = "uuid_branch") },
//    inverseJoinColumns = { @JoinColumn(name = "uuid_users") })
    @ManyToMany(mappedBy = "branches")
    private Set<ApplicationUsers> users;

    public ApplicationBranch() {
    }

    public ApplicationBranch(ApplicationBranchRequestDTO applicationBranchRequestDTO) {
        uuidBranch = applicationBranchRequestDTO.getId() != null ? UUID.fromString(applicationBranchRequestDTO.getId()) : null;
        nameBranch = applicationBranchRequestDTO.getText();
    }

    public ApplicationBranch(ApplicationBranchRequestOriginalDTO requestDTO) {
        uuidBranch = requestDTO.getUuidBranch() != null ? UUID.fromString(requestDTO.getUuidBranch()) : null;
        nameBranch = requestDTO.getNameBranch();
    }


    public UUID getUuidBranch() {
        return uuidBranch;
    }

    public void setUuidBranch(UUID uuidBranch) {
        this.uuidBranch = uuidBranch;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public Set<ApplicationUsers> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationUsers> users) {
        this.users = users;
    }

    public Select2ResponseDTO toSelect2ResponseDTO() {
        return new Select2ResponseDTO(this.uuidBranch.toString(), this.nameBranch, false);
    }
}
