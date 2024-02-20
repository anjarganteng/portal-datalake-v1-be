package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.IntercompanyTransactionGroup;
import id.co.telkomsigma.util.embeddables.EmbedIntercompanyTransactionGroup;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface IntercompanyTransactionGroupRepository extends DataTablesRepository<IntercompanyTransactionGroup, EmbedIntercompanyTransactionGroup>, JpaRepository<IntercompanyTransactionGroup, EmbedIntercompanyTransactionGroup> {
    
}
