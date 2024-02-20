package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ImportExcelLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */

public interface ImportExcelLogRepository extends DataTablesRepository<ImportExcelLog, Long>, JpaRepository<ImportExcelLog, Long> {
    public Page<ImportExcelLog> findAll(Pageable pageable);
}
