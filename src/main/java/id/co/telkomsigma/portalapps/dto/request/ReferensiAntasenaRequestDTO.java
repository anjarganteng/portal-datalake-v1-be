package id.co.telkomsigma.portalapps.dto.request;

import java.io.Serializable;

/**
 * @author satiya
 */
public class ReferensiAntasenaRequestDTO implements Serializable {

    private static final long serialVersionUID = 4902959243552228882L;

    private Long no;
    private String sandiExisting;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getSandiExisting() {
        return sandiExisting;
    }

    public void setSandiExisting(String sandiExisting) {
        this.sandiExisting = sandiExisting;
    }
}
