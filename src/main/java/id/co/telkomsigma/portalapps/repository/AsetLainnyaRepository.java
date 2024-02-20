package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.AsetLainnya;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface AsetLainnyaRepository extends DataTablesRepository<AsetLainnya, String>, JpaRepository<AsetLainnya, String> {
    public Page<AsetLainnya> findAll(Pageable pageable);
}
