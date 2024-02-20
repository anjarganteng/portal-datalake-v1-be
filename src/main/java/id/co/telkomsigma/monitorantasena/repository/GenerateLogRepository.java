package id.co.telkomsigma.monitorantasena.repository;

import id.co.telkomsigma.monitorantasena.model.GenerateLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author satiya
 */
@Repository
public interface GenerateLogRepository extends DataTablesRepository<GenerateLog, Long>, JpaRepository<GenerateLog, Long> {
    public Page<GenerateLog> findAll(Pageable pageable);
}
