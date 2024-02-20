package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.PostEmploymentBenefitsEmployeeId;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface PostEmploymentBenefitsEmployeeIdRepository extends DataTablesRepository<PostEmploymentBenefitsEmployeeId, String>, JpaRepository<PostEmploymentBenefitsEmployeeId, String> {
    
}
