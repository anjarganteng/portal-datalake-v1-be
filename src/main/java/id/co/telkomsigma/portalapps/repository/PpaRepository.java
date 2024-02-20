package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Ppa;
import id.co.telkomsigma.util.embeddables.EmbedPpa;
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
public interface PpaRepository extends DataTablesRepository<Ppa, EmbedPpa>, JpaRepository<Ppa, EmbedPpa> {

    public Page<Ppa> findAll(Pageable pageable);

    public Optional<Ppa> findByEmbedPpaSandiJenisAndEmbedPpaCabangAndEmbedPpaWilayahAndEmbedPpaMataUangAndEmbedPpaTglMulai(String sandiJenis, String cabang, String wilayah, String mataUang, String tglMulai);

    @Query(value = "DELETE " +
            "FROM tabelppa " +
            "WHERE sandi_jenis = ?1 " +
            "AND cabang = ?2 " +
            "AND wilayah = ?3 " +
            "AND mata_uang = ?4 " +
            "AND tgl_mulai = ?5", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedPpaSandiJenisAndEmbedPpaCabangAndEmbedPpaWilayahAndEmbedPpaMataUangAndEmbedPpaTglMulai(String sandiJenis, String cabang, String wilayah, String mataUang, String tglMulai);
}

