package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ParamArusKas;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author satiya
 */
public interface ParamArusKasRepository extends DataTablesRepository<ParamArusKas, String>, JpaRepository<ParamArusKas, String> {

    public List<ParamArusKas> findByFlagKbu(boolean flagKbu);

    public List<ParamArusKas> findByFlagRpp(boolean flagRpp);
}
