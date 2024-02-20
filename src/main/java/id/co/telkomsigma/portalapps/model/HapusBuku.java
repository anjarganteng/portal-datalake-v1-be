package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.HapusBukuRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelhbp")
public class HapusBuku {

    @Id
    @Column(name = "sandi_antasena")
    private String sandiAntasena;

    @Column(name = "deskripsi_sandi")
    private String deskripsiSandi;

    @Column(name = "rupiah")
    private String rupiah;

    @Column(name = "valas")
    private String valas;

    public HapusBuku() {
    }

    public HapusBuku(HapusBukuRequestDTO hapusBukuRequestDTO) {
        sandiAntasena = hapusBukuRequestDTO.getSandiAntasena();
        deskripsiSandi = hapusBukuRequestDTO.getDeskripsiSandi();
        rupiah = hapusBukuRequestDTO.getRupiah();
        valas = hapusBukuRequestDTO.getValas();
    }

    public String getSandiAntasena() {
        return sandiAntasena;
    }

    public void setSandiAntasena(String sandiAntasena) {
        this.sandiAntasena = sandiAntasena;
    }

    public String getDeskripsiSandi() {
        return deskripsiSandi;
    }

    public void setDeskripsiSandi(String deskripsiSandi) {
        this.deskripsiSandi = deskripsiSandi;
    }

    public String getRupiah() {
        return rupiah;
    }

    public void setRupiah(String rupiah) {
        this.rupiah = rupiah;
    }

    public String getValas() {
        return valas;
    }

    public void setValas(String valas) {
        this.valas = valas;
    }
}
