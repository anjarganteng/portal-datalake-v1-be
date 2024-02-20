package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.MasterCif;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author satiya
 */
public interface MasterCifRepository extends DataTablesRepository<MasterCif, String>, JpaRepository<MasterCif, String> {

    public Optional<MasterCif> findByNoIdentitas(String noIdentitas);
}
