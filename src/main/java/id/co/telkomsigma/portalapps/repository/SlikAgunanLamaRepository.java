package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.SlikAgunanLama;
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
public interface SlikAgunanLamaRepository extends DataTablesRepository<SlikAgunanLama, EmbedSlikAgunanLama>, JpaRepository<SlikAgunanLama, EmbedSlikAgunanLama> {

    public Page<SlikAgunanLama> findAll(Pageable pageable);

    public Optional<SlikAgunanLama> findByEmbedSlikAgunanLamaNoSlikLamaAndEmbedSlikAgunanLamaNoAgunanLama(String noSlikLama, String noAgunanLama);

    @Query(value = "DELETE " +
            "FROM tabelslikagunanlama " +
            "WHERE no_slik_lama = ?1 " +
            "AND no_agunan_lama = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedSlikAgunanLamaNoSlikLamaAndEmbedSlikAgunanLamaNoAgunanLama(String noSlikLama, String noAgunanLama);
}
