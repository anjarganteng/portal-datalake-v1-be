package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.IntercompanyTransactionKbank;
import id.co.telkomsigma.util.embeddables.EmbedIntercompanyTransactionKbank;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface IntercompanyTransactionKbankRepository extends DataTablesRepository<IntercompanyTransactionKbank, EmbedIntercompanyTransactionKbank>, JpaRepository<IntercompanyTransactionKbank, EmbedIntercompanyTransactionKbank> {
    
}
