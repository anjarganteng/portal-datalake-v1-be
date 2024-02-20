package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.CifToFi;
import id.co.telkomsigma.util.embeddables.EmbedCifToFi;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface CifToFiRepository extends DataTablesRepository<CifToFi, EmbedCifToFi>, JpaRepository<CifToFi, EmbedCifToFi> {
    
}
