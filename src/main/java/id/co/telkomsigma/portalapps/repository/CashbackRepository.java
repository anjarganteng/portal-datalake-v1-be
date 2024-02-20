package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Cashback;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface CashbackRepository extends DataTablesRepository<Cashback, String>, JpaRepository<Cashback, String> {
}
