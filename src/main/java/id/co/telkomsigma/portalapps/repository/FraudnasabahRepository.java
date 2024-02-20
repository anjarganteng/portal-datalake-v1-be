package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Fraudnasabah;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface FraudnasabahRepository extends DataTablesRepository<Fraudnasabah, String>, JpaRepository<Fraudnasabah, String> {
}
