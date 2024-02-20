package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.OtherBenefitsPaid;
import id.co.telkomsigma.portalapps.model.Wesel;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface OtherBenefitsPaidRepository extends DataTablesRepository<OtherBenefitsPaid, String>, JpaRepository<OtherBenefitsPaid, String> {
    
}
