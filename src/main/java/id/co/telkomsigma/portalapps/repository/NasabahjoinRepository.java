package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Nasabahjoin;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface NasabahjoinRepository extends DataTablesRepository<Nasabahjoin, String>, JpaRepository<Nasabahjoin, String> {
}
