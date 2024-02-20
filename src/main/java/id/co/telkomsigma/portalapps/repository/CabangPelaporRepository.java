package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.CabangPelapor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface CabangPelaporRepository extends DataTablesRepository<CabangPelapor, String>, JpaRepository<CabangPelapor, String> {
    public Page<CabangPelapor> findAll(Pageable pageable);
}
