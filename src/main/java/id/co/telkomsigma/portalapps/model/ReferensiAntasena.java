package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.ReferensiAntasenaRequestDTO;

import javax.persistence.*;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelreferensiantasena")
public class ReferensiAntasena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    @Column(name = "tabel_referensi")
    private String tabelReferensi;

    @Column(name = "sandi_referensi")
    private String sandiReferensi;

    @Column(name = "sandi_existing")
    private String sandiExisting;

    @Column(name = "label")
    private String label;

    public Long getNo() {
        return no;
    }

    public ReferensiAntasena() {
    }

    public ReferensiAntasena(ReferensiAntasenaRequestDTO referensiAntasenaRequestDTO) {
        sandiExisting = referensiAntasenaRequestDTO.getSandiExisting();
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
