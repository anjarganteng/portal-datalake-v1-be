package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.DeferredTax;
import id.co.telkomsigma.portalapps.model.EmbedDeferredTax;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface DeferredTaxRepository extends DataTablesRepository<DeferredTax, EmbedDeferredTax>, JpaRepository<DeferredTax, EmbedDeferredTax> {
    
}
