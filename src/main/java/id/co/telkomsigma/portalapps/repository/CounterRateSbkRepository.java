package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.CounterRateSbk;
import id.co.telkomsigma.util.embeddables.EmbedRateSbk;
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

public interface CounterRateSbkRepository extends DataTablesRepository<CounterRateSbk, EmbedRateSbk>, JpaRepository<CounterRateSbk, EmbedRateSbk> {
    public Page<CounterRateSbk> findAll(Pageable pageable);

    public Optional<CounterRateSbk> findByEmbedRateSbkJenisPenggunaanAndEmbedRateSbkJenisValuta(String jenisPenggunaan, String jenisValuta);

    @Query(value = "DELETE " +
            "FROM tabelcounterratesbk " +
            "WHERE JENIS_PENGGUNAAN = ?1 " +
            "AND JENIS_VALUTA = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedRateSbkJenisPenggunaanAndEmbedRateSbkJenisValuta(String jenisPenggunaan, String jenisValuta);
}
