package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.SummaryAdjustment;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface SummaryAdjustmentRepository extends DataTablesRepository<SummaryAdjustment, String>, JpaRepository<SummaryAdjustment, String> {
    
}