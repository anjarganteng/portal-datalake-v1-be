package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Cifjoinqq;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface CifjoinqqRepository extends DataTablesRepository<Cifjoinqq, String>, JpaRepository<Cifjoinqq, String> {
}
