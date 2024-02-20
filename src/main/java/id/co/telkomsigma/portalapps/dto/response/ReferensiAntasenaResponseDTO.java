package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ReferensiAntasena;

/**
 * @author satiya
 */
public class ReferensiAntasenaResponseDTO {

    private static final long serialVersionUID = 4791691349569405913L;

    private Long no;
    private String tabelReferensi;
    private String sandiReferensi;
    private String sandiExisting;
    private String label;

    public ReferensiAntasenaResponseDTO() {
    }

    public ReferensiAntasenaResponseDTO(ReferensiAntasena referensiAntasena) {
        no = referensiAntasena.getNo();
        tabelReferensi = referensiAntasena.getTabelReferensi();
        sandiReferensi = referensiAntasena.getSandiReferensi();
        sandiExisting = referensiAntasena.getSandiExisting();
        label = referensiAntasena.getLabel();
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTabelReferensi() {
        return tabelReferensi;
    }

    public void setTabelReferensi(String tabelReferensi) {
        this.tabelReferensi = tabelReferensi;
    }

    public String getSandiReferensi() {
        return sandiReferensi;
    }

    public void setSandiReferensi(String sandiReferensi) {
        this.sandiReferensi = sandiReferensi;
    }

    public String getSandiExisting() {
        return sandiExisting;
    }

    public void setSandiExisting(String sandiExisting) {
        this.sandiExisting = sandiExisting;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
