package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Kurs;
import id.co.telkomsigma.util.embeddables.EmbedKurs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface KursRepository extends DataTablesRepository<Kurs, EmbedKurs>, JpaRepository<Kurs, EmbedKurs> {
    public Page<Kurs> findAll(Pageable pageable);

    public Optional<Kurs> findByEmbedKursMataUangAndEmbedKursTanggalAndEmbedKursJenis(String mataUang, String tanggal, String jenis);

    @Query(value = "DELETE " +
            "FROM tabelkurs " +
            "WHERE mata_uang = ?1 " +
            "AND tanggal = ?2 " + 
            "AND jenis = ?3 ", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByEmbedKursMataUangAndEmbedKursTanggalAndEmbedKursJenis(String mataUang, String tanggal, String jenis);
}
