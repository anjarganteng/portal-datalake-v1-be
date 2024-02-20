package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.PengecualianKodeAgunan;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PengecualianKodeAgunanRepository extends DataTablesRepository<PengecualianKodeAgunan, String>, JpaRepository<PengecualianKodeAgunan, String> {

}
