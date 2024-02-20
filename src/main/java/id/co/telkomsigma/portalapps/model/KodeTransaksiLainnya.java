package id.co.telkomsigma.portalapps.model;

import id.co.telkomsigma.portalapps.dto.request.KodeTransaksiLainnyaRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author satiya
 */
@Entity
@Table(name = "tabelkodetransaksilainnya")
public class KodeTransaksiLainnya {

    @Id
    @Column(name = "kode_transaksi")
    private String kodeTransaksi;

    public KodeTransaksiLainnya() {
    }

    public KodeTransaksiLainnya(KodeTransaksiLainnyaRequestDTO kodeTransaksiLainnyaRequestDTO) {
        kodeTransaksi = kodeTransaksiLainnyaRequestDTO.getKodeTransaksi();
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }
}
