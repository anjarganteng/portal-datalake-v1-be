package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Coa;
import id.co.telkomsigma.util.embeddables.EmbedCoa;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author satiya
 */
public interface CoaRepository extends DataTablesRepository<Coa, EmbedCoa>, JpaRepository<Coa, EmbedCoa> {

    public Optional<Coa> findByEmbedCoaCoaIndukAndEmbedCoaCoaDetail(String coaInduk, String coaDetail);

    @Query(value = "DELETE " +
            "FROM tabelcoa " +
            "WHERE coa_induk = ?1 " +
            "AND coa_detail = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedCoaCoaIndukAndEmbedCoaCoaDetail(String coaInduk, String coaDetail);
}
