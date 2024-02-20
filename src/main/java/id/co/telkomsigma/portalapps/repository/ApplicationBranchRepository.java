package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ApplicationBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author satiya
 */

public interface ApplicationBranchRepository extends DataTablesRepository<ApplicationBranch, UUID>, JpaRepository<ApplicationBranch, UUID> {
    public Page<ApplicationBranch> findAll(Pageable pageable);

    public List<ApplicationBranch> findByNameBranchContaining(String nameBranch, Pageable pageable);
}
