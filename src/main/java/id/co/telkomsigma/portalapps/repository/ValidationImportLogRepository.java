package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ValidationImportLog;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author satiya
 */
public interface ValidationImportLogRepository extends DataTablesRepository<ValidationImportLog, Long>, JpaRepository<ValidationImportLog, Long> {

    @Query(value = "TRUNCATE TABLE validation_import_log", nativeQuery = true)
    @Modifying
    @Transactional
    void truncateTable();
}
