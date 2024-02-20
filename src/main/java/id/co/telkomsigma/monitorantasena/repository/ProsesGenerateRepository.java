package id.co.telkomsigma.monitorantasena.repository;

import id.co.telkomsigma.monitorantasena.model.ProsesGenerate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author satiya
 */

public interface ProsesGenerateRepository extends JpaRepository<ProsesGenerate, Long> {

    public Optional<ProsesGenerate> findByNamaProsesAndStatusProses(String namaProses, String statusProses);
}

