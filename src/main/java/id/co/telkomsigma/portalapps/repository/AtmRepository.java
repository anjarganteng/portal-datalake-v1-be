package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Atm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface AtmRepository extends DataTablesRepository<Atm, String>, JpaRepository<Atm, String> {
    public Page<Atm> findAll(Pageable pageable);
}
