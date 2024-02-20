package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Fraudnasabah;

/**
 * @author satiya
 */
public class FraudnasabahResponseDTO {

    private static final long serialVersionUID = -7852759357098744609L;

    private String noCif;

    public FraudnasabahResponseDTO() {
    }

    public FraudnasabahResponseDTO(Fraudnasabah fraudnasabah) {
        noCif = fraudnasabah.getNoCif();
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }
}
