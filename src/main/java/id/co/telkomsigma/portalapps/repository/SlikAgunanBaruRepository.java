package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.SlikAgunanBaru;
import id.co.telkomsigma.util.embeddables.EmbedSlikAgunanLama;
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
public interface SlikAgunanBaruRepository extends DataTablesRepository<SlikAgunanBaru, EmbedSlikAgunanLama>, JpaRepository<SlikAgunanBaru, EmbedSlikAgunanLama> {

    public Page<SlikAgunanBaru> findAll(Pageable pageable);

    public Optional<SlikAgunanBaru> findByEmbedSlikAgunanBaruNoValidAndEmbedSlikAgunanBaruNoAgunanBaru(String noValid, String noAgunanBaru);

    @Query(value = "DELETE " +
            "FROM tabelslikagunanbaru " +
            "WHERE no_valid = ?1 " +
            "AND no_agunan_baru = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedSlikAgunanBaruNoValidAndEmbedSlikAgunanBaruNoAgunanBaru(String noValid, String noAgunanBaru);
}
