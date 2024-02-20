package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Lps;
import id.co.telkomsigma.util.embeddables.EmbedLps;
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

public interface LpsRepository extends DataTablesRepository<Lps, EmbedLps>, JpaRepository<Lps, EmbedLps> {

    public Page<Lps> findAll(Pageable pageable);

    public Optional<Lps> findByEmbedLpsTglMulaiAndEmbedLpsJenisValuta(String tglMulai, String jenisValuta);

    @Query(value = "DELETE " +
            "FROM tabelbungalps " +
            "WHERE tanggal_mulai = ?1 " +
            "AND jenis_valuta = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedLpsTglMulaiAndEmbedLpsJenisValuta(String tglMulai, String jenisValuta);
}
