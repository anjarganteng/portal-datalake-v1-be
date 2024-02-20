package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Securities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface SecuritiesRepository extends DataTablesRepository<Securities, String>, JpaRepository<Securities, String> {
    
}
