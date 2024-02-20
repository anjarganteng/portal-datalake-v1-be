package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ApplicationBranchRequestOriginalDTO implements Serializable {

    private static final long serialVersionUID = 4916603208332828861L;

    private String uuidBranch;
    private String nameBranch;
    private boolean isNew;

    public String getUuidBranch() {
        return uuidBranch;
    }

    public void setUuidBranch(String uuidBranch) {
        this.uuidBranch = uuidBranch;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}

