package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Sandi;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author satiya
 */
public interface SandiRepository extends DataTablesRepository<Sandi, String>, JpaRepository<Sandi, String> {

    @Query(value = "SELECT * FROM tabelsandi WHERE TIPE_SANDI = ?1", nativeQuery = true)
    public List<Sandi> getAllByTipeSandi(String tipeSandi);
}
