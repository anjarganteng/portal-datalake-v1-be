package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.Kpmm;
import java.math.BigDecimal;

/**
 *
 * @author radit
 */
public class KpmmResponseDTO {
    
    private static final long serialVersionUID = 1L;

    private String kodeForm;
    private String kodeKomponen;
    private String namaKomponen;
    private BigDecimal jumlah;

    public KpmmResponseDTO() {
    }

    public KpmmResponseDTO(Kpmm kpmm) {
        this.kodeForm = kpmm.getEmbedKpmm().getKodeForm();
        this.kodeKomponen = kpmm.getEmbedKpmm().getKodeKomponen();
        this.namaKomponen = kpmm.getNamaKomponen();
        this.jumlah = kpmm.getJumlah();
    }

    public String getKodeForm() {
        return kodeForm;
    }

    public void setKodeForm(String kodeForm) {
        this.kodeForm = kodeForm;
    }

    public String getKodeKomponen() {
        return kodeKomponen;
    }

    public void setKodeKomponen(String kodeKomponen) {
        this.kodeKomponen = kodeKomponen;
    }

    public String getNamaKomponen() {
        return namaKomponen;
    }

    public void setNamaKomponen(String namaKomponen) {
        this.namaKomponen = namaKomponen;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }
    
}
