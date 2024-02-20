package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.MapLoanType;
import id.co.telkomsigma.util.embeddables.EmbedMapLoanType;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface MapLoanTypeRepository extends DataTablesRepository<MapLoanType, EmbedMapLoanType>, JpaRepository<MapLoanType, EmbedMapLoanType> {
    
}
