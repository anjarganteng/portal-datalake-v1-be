package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Atb;
import id.co.telkomsigma.util.embeddables.EmbedAtb;
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

public interface AtbRepository extends DataTablesRepository<Atb, EmbedAtb>, JpaRepository<Atb, EmbedAtb> {

    public Page<Atb> findAll(Pageable pageable);

    public Optional<Atb> findByEmbedAtbJenisAsetAndEmbedAtbTglPerolehanAndEmbedAtbTglMulai(String jenisAset, String tglPerolehan, String tglMulai);

    @Query(value = "DELETE " +
            "FROM tabelatb " +
            "WHERE jenis_aset = ?1 " +
            "AND tgl_perolehan = ?2 " +
            "AND tgl_mulai = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedAtbJenisAsetAndEmbedAtbTglPerolehanAndEmbedAtbTglMulai(String jenisAset, String tglPerolehan, String tglMulai);
}
