package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.RekeningSlik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface RekeningSlikRepository extends DataTablesRepository<RekeningSlik, String>, JpaRepository<RekeningSlik, String> {
    public Page<RekeningSlik> findAll(Pageable pageable);
}
