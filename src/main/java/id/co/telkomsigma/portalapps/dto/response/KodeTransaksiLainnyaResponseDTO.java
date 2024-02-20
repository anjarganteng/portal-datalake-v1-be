package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.KodeTransaksiLainnya;

/**
 * @author satiya
 */
public class KodeTransaksiLainnyaResponseDTO {

    private static final long serialVersionUID = -1632262136629174719L;

    private String kodeTransaksi;

    public KodeTransaksiLainnyaResponseDTO() {
    }

    public KodeTransaksiLainnyaResponseDTO(KodeTransaksiLainnya kodeTransaksiLainnya) {
        kodeTransaksi = kodeTransaksiLainnya.getKodeTransaksi();
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }
}
