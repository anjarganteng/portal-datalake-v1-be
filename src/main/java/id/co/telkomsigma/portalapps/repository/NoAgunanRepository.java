package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.NoAgunan;
import id.co.telkomsigma.util.embeddables.EmbedNoAgunan;
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
public interface NoAgunanRepository extends DataTablesRepository<NoAgunan, EmbedNoAgunan>, JpaRepository<NoAgunan, EmbedNoAgunan> {

    public Page<NoAgunan> findAll(Pageable pageable);

    public Optional<NoAgunan> findByEmbedNoAgunanNoAgunanBaruAndEmbedNoAgunanNoAgunanLama(String noAgunanBaru, String noAgunanLama);

    @Query(value = "DELETE " +
            "FROM tabelnoagunan " +
            "WHERE no_agunan_baru = ?1 " +
            "AND no_agunan_lama = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedNoAgunanNoAgunanBaruAndEmbedNoAgunanNoAgunanLama(String noAgunanBaru, String noAgunanLama);
}
