package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.BankGuarantee;
import id.co.telkomsigma.util.embeddables.EmbedBankGuarantee;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface BankGuaranteeRepository extends DataTablesRepository<BankGuarantee, EmbedBankGuarantee>, JpaRepository<BankGuarantee, EmbedBankGuarantee> {
    
}
