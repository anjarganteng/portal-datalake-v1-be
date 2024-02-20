package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.MappingNcd;

/**
 * @author satiya
 */
public class MappingNcdResponseDTO {

    private static final long serialVersionUID = 471695881258154252L;

    private String noDeposito;
    private String noCif;

    public MappingNcdResponseDTO() {
    }

    public MappingNcdResponseDTO(MappingNcd mappingNcd) {
        noDeposito = mappingNcd.getNoDeposito();
        noCif = mappingNcd.getNoCif();
    }

    public String getNoDeposito() {
        return noDeposito;
    }

    public void setNoDeposito(String noDeposito) {
        this.noDeposito = noDeposito;
    }

    public String getNoCif() {
        return noCif;
    }

    public void setNoCif(String noCif) {
        this.noCif = noCif;
    }
}
