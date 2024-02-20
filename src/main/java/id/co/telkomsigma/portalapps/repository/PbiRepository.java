package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Pbi;
import id.co.telkomsigma.util.embeddables.EmbedPbi;
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

public interface PbiRepository extends DataTablesRepository<Pbi, EmbedPbi>, JpaRepository<Pbi, EmbedPbi> {

    public Page<Pbi> findAll(Pageable pageable);

    public Optional<Pbi> findByEmbedPbiKodeCoaAndEmbedPbiMataUangAndEmbedPbiMulai(String kodeCoa, String mataUang, String mulai);

    @Query(value = "DELETE " +
            "FROM tabelpbi " +
            "WHERE kode_coa = ?1 " +
            "AND mata_uang = ?2 " +
            "AND mulai = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedPbiKodeCoaAndEmbedPbiMataUangAndEmbedPbiMulai(String kodeCoa, String mataUang, String mulai);
}
