package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ApplicationUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author satiya
 */

public interface ApplicationUsersRepository extends DataTablesRepository<ApplicationUsers, UUID>, JpaRepository<ApplicationUsers, UUID> {
    public Page<ApplicationUsers> findAll(Pageable pageable);

    public Optional<ApplicationUsers> findByEmail(String email);

}
