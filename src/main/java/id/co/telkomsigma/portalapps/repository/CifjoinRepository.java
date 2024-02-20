package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Cifjoin;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author satiya
 */
public interface CifjoinRepository extends DataTablesRepository<Cifjoin, String>, JpaRepository<Cifjoin, String> {

    public Optional<Cifjoin> findByEmbedCifjoinCifAndEmbedCifjoinCifJoin(String cif, String cifJoin);

    @Query(value = "DELETE " +
            "FROM tabelcifjoin " +
            "WHERE CIF = ?1 " +
            "AND CIF_JOIN = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedCifjoinCifAndEmbedCifjoinCifJoin(String cif, String cifJoin);

    @Query(value = "SELECT * FROM tabelcifjoin WHERE CIF_JOIN = ?1 LIMIT 1", nativeQuery = true)
    public Optional<Cifjoin> findByEmbedCifjoinCifJoin(String cifJoin);
}
