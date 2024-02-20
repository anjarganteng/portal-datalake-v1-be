package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Wesel;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface WeselRepository extends DataTablesRepository<Wesel, String>, JpaRepository<Wesel, String> {
    
}
