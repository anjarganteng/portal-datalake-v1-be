package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ApplicationBranch;

/**
 * @author satiya
 */
public class ApplicationBranchResponseDTO extends BaseModelDTO {

    private static final long serialVersionUID = -2601545627975418211L;

    private String uuidBranch;
    private String nameBranch;

    public ApplicationBranchResponseDTO() {
    }

    public ApplicationBranchResponseDTO(ApplicationBranch applicationBranch) {
        uuidBranch = applicationBranch.getUuidBranch().toString();
        nameBranch = applicationBranch.getNameBranch();

        baseInfo(applicationBranch);
    }

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
}
