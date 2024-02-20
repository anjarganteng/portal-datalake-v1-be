package id.co.telkomsigma.monitorantasena.repository;

import id.co.telkomsigma.monitorantasena.model.ProsesManual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author satiya
 */
@Repository
public interface ProsesManualRepository extends DataTablesRepository<ProsesManual, String>, JpaRepository<ProsesManual, String> {
    public Page<ProsesManual> findAll(Pageable pageable);
}
