package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.util.embeddables.EmbedCoaBot;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author radit
 */
public interface CoaBotRepository extends DataTablesRepository<CoaBot, EmbedCoaBot>, JpaRepository<CoaBot, EmbedCoaBot> {

}
