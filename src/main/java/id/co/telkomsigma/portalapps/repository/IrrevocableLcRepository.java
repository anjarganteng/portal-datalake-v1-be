package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.IrrevocableLc;
import id.co.telkomsigma.util.embeddables.EmbedIrrevocableLc;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface IrrevocableLcRepository extends DataTablesRepository<IrrevocableLc, EmbedIrrevocableLc>, JpaRepository<IrrevocableLc, EmbedIrrevocableLc> {
    
}
