package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.PihakLawan;
import id.co.telkomsigma.util.embeddables.EmbedPihakLawan;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author satiya
 */
public interface PihakLawanRepository extends DataTablesRepository<PihakLawan, EmbedPihakLawan>, JpaRepository<PihakLawan, EmbedPihakLawan> {

    public Optional<PihakLawan> findByEmbedPihakLawanKdCabangAndEmbedPihakLawanIdPihakLawan(String kdCabang, String idPihakLawan);

    @Query(value = "DELETE " +
            "FROM tabelpihaklawan " +
            "WHERE KD_CABANG = ?1 " +
            "AND ID_PIHAK_LAWAN = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedPihakLawanKdCabangAndEmbedPihakLawanIdPihakLawan(String kdCabang, String idPihakLawan);
}
