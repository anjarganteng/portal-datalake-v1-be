package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Pts;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface PtsRepository extends DataTablesRepository<Pts, String>, JpaRepository<Pts, String> {

}
