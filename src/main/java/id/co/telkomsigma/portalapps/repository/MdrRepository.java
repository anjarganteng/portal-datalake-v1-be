package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Mdr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface MdrRepository extends DataTablesRepository<Mdr, String>, JpaRepository<Mdr, String> {
    public Page<Mdr> findAll(Pageable pageable);
}
