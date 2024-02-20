package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.KodeAgunan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface KodeAgunanRepository extends JpaRepository<KodeAgunan, String>, DataTablesRepository<KodeAgunan, String> {
    public Page<KodeAgunan> findAll(Pageable pageable);
}
