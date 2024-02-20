package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.PostEmploymentBenefits;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface PostEmploymentBenefitsRepository extends DataTablesRepository<PostEmploymentBenefits, String>, JpaRepository<PostEmploymentBenefits, String> {
    
}
