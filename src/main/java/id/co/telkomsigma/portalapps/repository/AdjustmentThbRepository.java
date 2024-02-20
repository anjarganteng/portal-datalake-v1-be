package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.AdjustmentThb;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface AdjustmentThbRepository extends DataTablesRepository<AdjustmentThb, String>, JpaRepository<AdjustmentThb, String> {
    
}
