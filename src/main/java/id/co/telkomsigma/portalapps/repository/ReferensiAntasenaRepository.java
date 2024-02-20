package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ReferensiAntasena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface ReferensiAntasenaRepository extends DataTablesRepository<ReferensiAntasena, Long>, JpaRepository<ReferensiAntasena, Long> {
    public Page<ReferensiAntasena> findAll(Pageable pageable);
}
