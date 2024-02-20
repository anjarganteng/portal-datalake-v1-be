package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ApplicationRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author satiya
 */

public interface ApplicationRolesRepository extends DataTablesRepository<ApplicationRoles, UUID>, JpaRepository<ApplicationRoles, UUID> {

    public Page<ApplicationRoles> findAll(Pageable pageable);

    public List<ApplicationRoles> findByNameRolesContaining(String nameRoles, Pageable pageable);
}