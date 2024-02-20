package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ArusKasKbu;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArusKasKbuRepository extends DataTablesRepository<ArusKasKbu, String>, JpaRepository<ArusKasKbu, String> {
}
