package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * @author satiya
 */
public class ApplicationUsersRequestDTO implements Serializable {

    private static final long serialVersionUID = -8979991224383284994L;

    private String uuidUsers;
    private String uuidRoles;

    private List<ApplicationBranchRequestDTO> branchList;

    private String email;
    private String fullname;
    private String phone;
    private String password;
    private boolean isActive;
    private boolean isNew;

    public String getUuidUsers() {
        return uuidUsers;
    }

    public void setUuidUsers(String uuidUsers) {
        this.uuidUsers = uuidUsers;
    }

    public String getUuidRoles() {
        return uuidRoles;
    }

    public void setUuidRoles(String uuidRoles) {
        this.uuidRoles = uuidRoles;
    }

    public List<ApplicationBranchRequestDTO> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<ApplicationBranchRequestDTO> branchList) {
        this.branchList = branchList;
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
