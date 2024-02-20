package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Pbl;
import id.co.telkomsigma.util.embeddables.EmbedPbl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author satiya
 */

public interface PblRepository extends DataTablesRepository<Pbl, EmbedPbl>, JpaRepository<Pbl, EmbedPbl> {

    public Page<Pbl> findAll(Pageable pageable);

    public Optional<Pbl> findByEmbedPblKodeCoaAndEmbedPblMataUangAndEmbedPblMulai(String kodeCoa, String mataUang, String mulai);

    @Query(value = "DELETE " +
            "FROM tabelpbl " +
            "WHERE kode_coa = ?1 " +
            "AND mata_uang = ?2 " +
            "AND mulai = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedPblKodeCoaAndEmbedPblMataUangAndEmbedPblMulai(String kodeCoa, String mataUang, String mulai);
}
