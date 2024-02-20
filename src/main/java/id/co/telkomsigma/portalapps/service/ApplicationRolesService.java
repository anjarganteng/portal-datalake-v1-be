package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesMenusRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesSimpleRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.Select2RequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationRolesMenusResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationRolesResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.Select2ResponseDTO;
import id.co.telkomsigma.portalapps.model.ApplicationRoles;
import id.co.telkomsigma.portalapps.model.ApplicationRolesMenus;
import id.co.telkomsigma.portalapps.model.ListMenu;
import id.co.telkomsigma.portalapps.repository.ApplicationRolesMenusRepository;
import id.co.telkomsigma.portalapps.repository.ApplicationRolesRepository;
import id.co.telkomsigma.portalapps.repository.ListMenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author satiya
 */
@Service
public class ApplicationRolesService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRolesService.class);

    private ApplicationRolesRepository applicationRolesRepository;
    private ListMenuRepository listMenuRepository;

    private ApplicationRolesMenusRepository applicationRolesMenusRepository;

    @Autowired
    public ApplicationRolesService(
            ApplicationRolesRepository applicationRolesRepository,
            ListMenuRepository listMenuRepository,
            ApplicationRolesMenusRepository applicationRolesMenusRepository
    ) {
        this.applicationRolesRepository = applicationRolesRepository;
        this.listMenuRepository = listMenuRepository;
        this.applicationRolesMenusRepository = applicationRolesMenusRepository;
    }

    public void save(ApplicationRolesRequestDTO applicationRolesRequestDTO) {
        ApplicationRoles applicationRoles = null;

        // logic save
        applicationRoles = applicationRolesRepository.findById(UUID.fromString(applicationRolesRequestDTO.getUuidRoles())).get();

        // mapping data for association table
        Set<ApplicationRolesMenus> setRoleMenus = new HashSet<>();
        for (ApplicationRolesMenusRequestDTO roleMenuDTO : applicationRolesRequestDTO.getMenuList()) {

            ListMenu listMenu = listMenuRepository.findById(roleMenuDTO.getMenuId()).get();
            ApplicationRolesMenus applicationRolesMenus = null;
            if (roleMenuDTO.getRolesMenusId() != null) {
                // do update
                applicationRolesMenus =
                        applicationRolesMenusRepository.findByRoleAndMenu(applicationRolesRequestDTO.getUuidRoles(), listMenu.getId()).get();

                applicationRolesMenus.setCanDownload(roleMenuDTO.isCanDownload());
                applicationRolesMenus.setCanRead(roleMenuDTO.isCanRead());
                applicationRolesMenus.setCanUpdate(roleMenuDTO.isCanUpdate());
                applicationRolesMenus.setCanRegenerate(roleMenuDTO.isCanRegenerate());
                applicationRolesMenus.setCanUpload(roleMenuDTO.isCanUpload());
                applicationRolesMenus.setIsAdmin(roleMenuDTO.isIsAdmin());
            } else {
                // do insert
                applicationRolesMenus = new ApplicationRolesMenus(roleMenuDTO);
                applicationRolesMenus.setApplicationRoles(applicationRoles);
                applicationRolesMenus.setListMenu(listMenu);

            }

            setRoleMenus.add(applicationRolesMenus);
        }

        applicationRoles.setRoleMenus(setRoleMenus);


        ApplicationRoles saved = applicationRolesRepository.save(applicationRoles);
        LOGGER.info("Successfully save ApplicationRoles with uuid_roles: " + saved.getUuidRoles());
    }

    public void save(ApplicationRolesSimpleRequestDTO applicationRolesSimpleRequestDTO) {
        ApplicationRoles applicationRoles = null;
        if (applicationRolesSimpleRequestDTO.getUuidRoles() != null) {
            // do update
            applicationRoles = applicationRolesRepository.findById(UUID.fromString(applicationRolesSimpleRequestDTO.getUuidRoles())).get();
            applicationRoles.setNameRoles(applicationRolesSimpleRequestDTO.getNameRoles());
        } else {
            //do insert
            applicationRoles = new ApplicationRoles(applicationRolesSimpleRequestDTO);
        }

        ApplicationRoles saved = applicationRolesRepository.save(applicationRoles);
        LOGGER.info("Successfully save ApplicationRoles with uuid_roles: " + saved.getUuidRoles());

    }

    public List<ApplicationRolesResponseDTO> getAll() {
        List<ApplicationRolesResponseDTO> applicationRolesResponseDTOs = null;
        List<ApplicationRoles> applicationRoless = applicationRolesRepository.findAll();
        if (!applicationRoless.isEmpty()) {
            applicationRolesResponseDTOs = new ArrayList<>();
            for (ApplicationRoles applicationRoles : applicationRoless) {

                // get and convert ApplicationRolesMenus into ApplicationRolesMenusResponseDTO
                List<ApplicationRolesMenusResponseDTO> rolesMenusResponseDTOs = new ArrayList<>();
                for (ApplicationRolesMenus data : applicationRoles.getRoleMenus()) {
                    ApplicationRolesMenusResponseDTO applicationRolesMenusResponseDTO = new ApplicationRolesMenusResponseDTO(data);

                    rolesMenusResponseDTOs.add(applicationRolesMenusResponseDTO);
                }

                // convert ApplicationRoles into ApplicationRolesResponseDTO
                ApplicationRolesResponseDTO applicationRolesResponseDTO = new ApplicationRolesResponseDTO(applicationRoles);
                applicationRolesResponseDTO.setMenuList(rolesMenusResponseDTOs);

                applicationRolesResponseDTOs.add(applicationRolesResponseDTO);
            }
        }
        return applicationRolesResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ApplicationRolesResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ApplicationRoles> dataTablesOutput = applicationRolesRepository.findAll(dataTablesInput);

        List<ApplicationRolesResponseDTO> applicationRolesResponseDTOs = new ArrayList<>();
        for (ApplicationRoles applicationRoles : dataTablesOutput.getData()) {
            List<ApplicationRolesMenusResponseDTO> rolesMenusResponseDTOs = new ArrayList<>();
            for (ApplicationRolesMenus data : applicationRoles.getRoleMenus()) {
                ApplicationRolesMenusResponseDTO applicationRolesMenusResponseDTO = new ApplicationRolesMenusResponseDTO(data);

                rolesMenusResponseDTOs.add(applicationRolesMenusResponseDTO);
            }

            // convert ApplicationRoles into ApplicationRolesResponseDTO
            ApplicationRolesResponseDTO applicationRolesResponseDTO = new ApplicationRolesResponseDTO(applicationRoles);
            applicationRolesResponseDTO.setMenuList(rolesMenusResponseDTOs);

            applicationRolesResponseDTOs.add(applicationRolesResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(applicationRolesResponseDTOs);

        return result;
    }

    public ApplicationRolesResponseDTO findById(String uuidRoles) {
        ApplicationRolesResponseDTO applicationRolesResponseDTO = null;
        Optional<ApplicationRoles> applicationRolesOpt = applicationRolesRepository.findById(UUID.fromString(uuidRoles));
        if (applicationRolesOpt.isPresent()) {
            ApplicationRoles applicationRoles = applicationRolesOpt.get();

            // get and convert ApplicationRolesMenus into ApplicationRolesMenusResponseDTO
            List<ApplicationRolesMenusResponseDTO> rolesMenusResponseDTOs = new ArrayList<>();
            for (ApplicationRolesMenus data : applicationRoles.getRoleMenus()) {
                ApplicationRolesMenusResponseDTO applicationRolesMenusResponseDTO = new ApplicationRolesMenusResponseDTO(data);

                rolesMenusResponseDTOs.add(applicationRolesMenusResponseDTO);
            }

            // convert ApplicationRoles into ApplicationRolesResponseDTO
            applicationRolesResponseDTO = new ApplicationRolesResponseDTO(applicationRoles);
            applicationRolesResponseDTO.setMenuList(rolesMenusResponseDTOs);
        }

        return applicationRolesResponseDTO;
    }

    public void deleteById(String uuidRoles) {
        Optional<ApplicationRoles> applicationRolesOpt = applicationRolesRepository.findById(UUID.fromString(uuidRoles));
        if (applicationRolesOpt.isPresent()) {
            applicationRolesRepository.deleteById(UUID.fromString(uuidRoles));
            LOGGER.info("Successfully delete ApplicationRoles with uuid_roles: " + uuidRoles);
        } else {
            LOGGER.info("Cannot find ApplicationRoles with uuid_roles: " + uuidRoles);
        }
    }

    public List<Select2ResponseDTO> getAllSelect2(Select2RequestDTO select2RequestDTO) {
        List<ApplicationRoles> applicationRoless = new ArrayList<ApplicationRoles>();
        PageRequest pageRequest = PageRequest.of(select2RequestDTO.getPage(), 5);

        if (select2RequestDTO.getSearch().getText() != null) {
            if (select2RequestDTO.getSearch().getText().equals("")) {
                applicationRoless = applicationRolesRepository.findAll(pageRequest).getContent();
            } else {
                applicationRoless = applicationRolesRepository.findByNameRolesContaining(select2RequestDTO.getSearch().getText(), pageRequest);
            }
        } else {
            applicationRoless = applicationRolesRepository.findAll(pageRequest).getContent();
        }

        List<Select2ResponseDTO> select2ResponseDTOs = new ArrayList<Select2ResponseDTO>();
        for (ApplicationRoles applicationRoles : applicationRoless) {
            select2ResponseDTOs.add(applicationRoles.toSelect2ResponseDTO());
        }

        return select2ResponseDTOs;
    }
}
