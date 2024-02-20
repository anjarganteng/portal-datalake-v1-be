package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.CounterRateSbs;
import id.co.telkomsigma.util.embeddables.EmbedRateSbs;
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

public interface CounterRateSbsRepository extends DataTablesRepository<CounterRateSbs, EmbedRateSbs>, JpaRepository<CounterRateSbs, EmbedRateSbs> {

    public Page<CounterRateSbs> findAll(Pageable pageable);

    public Optional<CounterRateSbs> findByEmbedRateSbsJenisInstrumenAndEmbedRateSbsJenisValutaAndEmbedRateSbsJangkaWaktu(String jenisInstrumen, String jenisValuta, String jangkaWaktu);

    @Query(value = "DELETE " +
            "FROM tabelcounterratesbs " +
            "WHERE JENIS_INSTRUMEN = ?1 " +
            "AND JENIS_VALUTA = ?2 " +
            "AND JANGKA_WAKTU = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedRateSbsJenisInstrumenAndEmbedRateSbsJenisValutaAndEmbedRateSbsJangkaWaktu(String jenisInstrumen, String jenisValuta, String jangkaWaktu);
}
