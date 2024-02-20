package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.GolPihakLawan;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface GolPihakLawanRepository extends DataTablesRepository<GolPihakLawan, String>, JpaRepository<GolPihakLawan, String> {
}
