package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.RekeningSlik;

/**
 * @author satiya
 */
public class RekeningSlikResponseDTO {

    private static final long serialVersionUID = 7555831994362790086L;

    private String noValid;
    private String noSlik;

    public RekeningSlikResponseDTO() {
    }

    public RekeningSlikResponseDTO(RekeningSlik rekeningSlik) {
        noValid = rekeningSlik.getNoValid();
        noSlik = rekeningSlik.getNoSlik();
    }

    public String getNoValid() {
        return noValid;
    }

    public void setNoValid(String noValid) {
        this.noValid = noValid;
    }

    public String getNoSlik() {
        return noSlik;
    }

    public void setNoSlik(String noSlik) {
        this.noSlik = noSlik;
    }
}
