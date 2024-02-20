package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ReverseRepoSupport;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface ReverseRepoSupportRepository extends DataTablesRepository<ReverseRepoSupport, String>, JpaRepository<ReverseRepoSupport, String> {
    
}
