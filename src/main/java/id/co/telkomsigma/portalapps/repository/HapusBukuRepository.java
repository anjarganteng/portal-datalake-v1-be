package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.HapusBuku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface HapusBukuRepository extends DataTablesRepository<HapusBuku, String>, JpaRepository<HapusBuku, String> {
    public Page<HapusBuku> findAll(Pageable pageable);
}
