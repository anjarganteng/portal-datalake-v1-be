package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.PihakTerkait;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface PihakTerkaitRepository extends DataTablesRepository<PihakTerkait, String>, JpaRepository<PihakTerkait, String> {
    public Page<PihakTerkait> findAll(Pageable pageable);
}
