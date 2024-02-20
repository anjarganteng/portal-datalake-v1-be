package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author satiya
 */
public class LimitSimpanRequestDTO implements Serializable {

    private static final long serialVersionUID = -5869778173754603457L;

    private String id;
    private BigDecimal nominalDijamin;
    private boolean isNew;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getNominalDijamin() {
        return nominalDijamin;
    }

    public void setNominalDijamin(BigDecimal nominalDijamin) {
        this.nominalDijamin = nominalDijamin;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
