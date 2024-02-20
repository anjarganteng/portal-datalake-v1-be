package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.MappingNcd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface MappingNcdRepository extends DataTablesRepository<MappingNcd, String>, JpaRepository<MappingNcd, String> {
    public Page<MappingNcd> findAll(Pageable pageable);
}
