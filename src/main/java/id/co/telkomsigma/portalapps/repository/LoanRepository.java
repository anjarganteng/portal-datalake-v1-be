package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Loan;
import id.co.telkomsigma.util.embeddables.EmbedLoan;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface LoanRepository extends DataTablesRepository<Loan, EmbedLoan>, JpaRepository<Loan, EmbedLoan> {
    
}
