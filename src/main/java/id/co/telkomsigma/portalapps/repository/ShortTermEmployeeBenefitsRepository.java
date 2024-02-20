package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ShortTermEmployeeBenefits;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface ShortTermEmployeeBenefitsRepository extends DataTablesRepository<ShortTermEmployeeBenefits, String>, JpaRepository<ShortTermEmployeeBenefits, String> {
    
}
