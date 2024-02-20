package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.LiabilitasAntarKantor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface LiabilitasAntarKantorRepository extends DataTablesRepository<LiabilitasAntarKantor, String>, JpaRepository<LiabilitasAntarKantor, String> {
    public Page<LiabilitasAntarKantor> findAll(Pageable pageable);
}
