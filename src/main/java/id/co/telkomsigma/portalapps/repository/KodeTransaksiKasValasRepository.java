package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.KodeTransaksiKasValas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface KodeTransaksiKasValasRepository extends DataTablesRepository<KodeTransaksiKasValas, String>, JpaRepository<KodeTransaksiKasValas, String> {
    public Page<KodeTransaksiKasValas> findAll(Pageable pageable);
}
