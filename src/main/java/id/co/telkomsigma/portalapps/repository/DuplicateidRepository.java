package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Duplicateid;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author satiya
 */
public interface DuplicateidRepository extends DataTablesRepository<Duplicateid, String>, JpaRepository<Duplicateid, String> {
}
