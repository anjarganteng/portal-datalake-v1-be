package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.LimitSimpanan;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface LimitSimpananRepository extends DataTablesRepository<LimitSimpanan, String>, JpaRepository<LimitSimpanan, String> {
}
