package id.co.telkomsigma.portalapps.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import id.co.telkomsigma.portalapps.model.ApplicationUsers;

import java.util.List;

public class ApplicationUsersResponseDTO extends BaseModelDTO {

    private static final long serialVersionUID = -6531275968620110704L;

    private String uuidUsers;

    private ApplicationRolesResponseDTO roles;

    private String email;
    private String fullname;

    @JsonInclude(Include.NON_NULL)
    private List<ApplicationBranchResponseDTO> branchList;

    private String phone;

    private boolean isActive;

    public ApplicationUsersResponseDTO() {
    }

    public ApplicationUsersResponseDTO(ApplicationUsers applicationUsers) {
        uuidUsers = applicationUsers.getUuidUsers().toString();
        email = applicationUsers.getEmail();
        fullname = applicationUsers.getFullname();
        phone = applicationUsers.getPhone();
        isActive = applicationUsers.isActive();

        baseInfo(applicationUsers);
    }

    public String getUuidUsers() {
        return uuidUsers;
    }

    public void setUuidUsers(String uuidUsers) {
        this.uuidUsers = uuidUsers;
    }

    public ApplicationRolesResponseDTO getRoles() {
        return roles;
    }

    public void setRoles(ApplicationRolesResponseDTO roles) {
        this.roles = roles;
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

    public List<ApplicationBranchResponseDTO> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<ApplicationBranchResponseDTO> branchList) {
        this.branchList = branchList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
