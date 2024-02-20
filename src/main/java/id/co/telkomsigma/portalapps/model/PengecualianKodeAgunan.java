package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.PengecualianKodeAgunanRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelpengecualiankodeagunan")
public class PengecualianKodeAgunan {

    @Id
    @Column(name = "kode_agunan_core")
    private String kodeAgunanCore;

    public PengecualianKodeAgunan() {
    }

    public PengecualianKodeAgunan(PengecualianKodeAgunanRequestDTO pengecualianKodeAgunanRequestDTO) {
        kodeAgunanCore = pengecualianKodeAgunanRequestDTO.getKodeAgunanCore();
    }

    public String getKodeAgunanCore() {
        return kodeAgunanCore;
    }

    public void setKodeAgunanCore(String kodeAgunanCore) {
        this.kodeAgunanCore = kodeAgunanCore;
    }
}
