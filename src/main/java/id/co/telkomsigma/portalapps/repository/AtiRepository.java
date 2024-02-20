package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Ati;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface AtiRepository extends DataTablesRepository<Ati, String>, JpaRepository<Ati, String> {

    public Page<Ati> findAll(Pageable pageable);
}
