package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ListMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author satiya
 */

public interface ListMenuRepository extends DataTablesRepository<ListMenu, Long>, JpaRepository<ListMenu, Long> {
    public Page<ListMenu> findAll(Pageable pageable);

    public List<ListMenu> findByNamaMenuContaining(String namaMenu, Pageable pageable);

    public List<ListMenu> findByIsIsTableAndForm(boolean isIsTable, String form);

    @Query(value = "SELECT * " +
            "FROM tabellistmenu_new m " +
            "join application_roles_menus rm " +
            "on (m.id=rm.menu_id) " +
            "where rm.uuid_roles = uuid_to_bin(?1)", nativeQuery = true)
    public List<ListMenu> findByRole(String uuidRoles);
}
