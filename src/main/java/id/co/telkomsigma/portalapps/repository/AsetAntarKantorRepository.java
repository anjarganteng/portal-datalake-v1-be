package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.AsetAntarKantor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface AsetAntarKantorRepository extends DataTablesRepository<AsetAntarKantor, String>, JpaRepository<AsetAntarKantor, String> {
    public Page<AsetAntarKantor> findAll(Pageable pageable);
}
