package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.ApplicationRolesMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author satiya
 */

public interface ApplicationRolesMenusRepository extends JpaRepository<ApplicationRolesMenus, Long> {

    @Query(value = "SELECT * " +
            "FROM application_roles_menus " +
            "WHERE uuid_roles = uuid_to_bin(?1) " +
            "AND menu_id = ?2", nativeQuery = true)
    public Optional<ApplicationRolesMenus> findByRoleAndMenu(String uuidRoles, Long menuId);

    @Query(value = "SELECT * " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE path_backend = ?1 AND uuid_roles = uuid_to_bin(?2)", nativeQuery = true)
    public Optional<ApplicationRolesMenus> findByUrl(String url, String uuidRoles);

    @Query(value = "SELECT p.link_tabel " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE uuid_roles = uuid_to_bin(?1) AND form = 'antasena' AND can_read = 1 AND link_tabel IS NOT NULL", nativeQuery = true)
    public String[] findMenuLinkTabelAntasena(String uuidRoles);

    @Query(value = "SELECT p.link_tabel " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE uuid_roles = uuid_to_bin(?1) AND form = 'parameter' AND can_read = 1 AND link_tabel IS NOT NULL", nativeQuery = true)
    public String[] findMenuLinkTabelParameter(String uuidRoles);

    @Query(value = "SELECT p.link_tabel " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE uuid_roles = uuid_to_bin(?1) AND form = 'lps' AND can_read = 1 AND link_tabel IS NOT NULL", nativeQuery = true)
    public String[] findMenuLinkTabelLps(String uuidRoles);

    @Query(value = "SELECT p.link_tabel " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE uuid_roles = uuid_to_bin(?1) AND form = 'settings' AND can_read = 1 AND link_tabel IS NOT NULL", nativeQuery = true)
    public String[] findMenuLinkTabelSettings(String uuidRoles);

    @Query(value = "SELECT p.link_tabel " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE uuid_roles = uuid_to_bin(?1) AND form = 'log' AND can_read = 1 AND link_tabel IS NOT NULL", nativeQuery = true)
    public String[] findMenuLinkTabelLog(String uuidRoles);
    
    
    @Query(value = "SELECT p.link_tabel " +
            "FROM application_roles_menus u " +
            "join tabellistmenu_new p " +
            "ON u.menu_id=p.id " +
            "WHERE uuid_roles = uuid_to_bin(?1) AND form = 'bot' AND can_read = 1 AND link_tabel IS NOT NULL", nativeQuery = true)
    public String[] findMenuLinkTabelBot(String uuidRoles);

}
