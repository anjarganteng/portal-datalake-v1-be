package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.SetoranJaminan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface SetoranJaminanRepository extends DataTablesRepository<SetoranJaminan, String>, JpaRepository<SetoranJaminan, String> {
    public Page<SetoranJaminan> findAll(Pageable pageable);
}
