package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.KodeTransaksiLainnya;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface KodeTransaksiLainnyaRepository extends DataTablesRepository<KodeTransaksiLainnya, String>, JpaRepository<KodeTransaksiLainnya, String> {
    public Page<KodeTransaksiLainnya> findAll(Pageable pageable);
}
