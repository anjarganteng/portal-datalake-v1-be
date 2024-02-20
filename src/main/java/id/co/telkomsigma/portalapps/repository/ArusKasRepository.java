package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ArusKas;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArusKasRepository extends DataTablesRepository<ArusKas, String>, JpaRepository<ArusKas, String> {

}
