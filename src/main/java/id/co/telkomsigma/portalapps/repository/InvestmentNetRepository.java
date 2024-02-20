package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.InvestmentNet;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface InvestmentNetRepository extends DataTablesRepository<InvestmentNet, String>, JpaRepository<InvestmentNet, String> {
    
}
