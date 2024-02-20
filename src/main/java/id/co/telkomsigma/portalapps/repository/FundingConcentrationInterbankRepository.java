package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.FundingConcentrationInterbank;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface FundingConcentrationInterbankRepository extends DataTablesRepository<FundingConcentrationInterbank, String>, JpaRepository<FundingConcentrationInterbank, String> {
    
}
