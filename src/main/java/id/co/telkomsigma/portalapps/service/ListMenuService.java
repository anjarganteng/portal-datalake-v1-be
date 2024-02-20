package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.ListMenuRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.Select2RequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationRolesMenusResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.ListMenuOnlyModulResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.ListMenuResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.Select2ResponseDTO;
import id.co.telkomsigma.portalapps.model.ApplicationRolesMenus;
import id.co.telkomsigma.portalapps.model.ListMenu;
import id.co.telkomsigma.portalapps.repository.ApplicationRolesMenusRepository;
import id.co.telkomsigma.portalapps.repository.ListMenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author satiya
 */
@Service
public class ListMenuService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ListMenuService.class);

    private ListMenuRepository listMenuRepository;
    private ApplicationRolesMenusRepository applicationRolesMenusRepository;

    @Autowired
    public ListMenuService(
            ListMenuRepository listMenuRepository,
            ApplicationRolesMenusRepository applicationRolesMenusRepository
    ) {
        this.listMenuRepository = listMenuRepository;
        this.applicationRolesMenusRepository = applicationRolesMenusRepository;
    }

    public void save(ListMenuRequestDTO listMenuRequestDTO) {
        ListMenu listMenu = null;
        if (listMenuRequestDTO.getId() != null) {
            // do update
        } else {
            // do insert
            listMenu = new ListMenu(listMenuRequestDTO);
        }

        ListMenu saved = listMenuRepository.save(listMenu);
        LOGGER.info("Successfully save ListMenu with id: " + saved.getId());
    }

    public List<ListMenuResponseDTO> getAll(String form) {
        List<ListMenuResponseDTO> listMenuResponseDTOS = null;

        List<ListMenu> listMenus = listMenuRepository.findByIsIsTableAndForm(true, form);

        if (!listMenus.isEmpty()) {
            listMenuResponseDTOS = new ArrayList<>();
            for (ListMenu listMenu : listMenus) {
                ListMenuResponseDTO listMenuResponseDTO = new ListMenuResponseDTO(listMenu);
                listMenuResponseDTO.setChildrens(listMenu.getChildrens());

                listMenuResponseDTOS.add(listMenuResponseDTO);
            }
        }

        return listMenuResponseDTOS;
    }


    public List<ListMenuOnlyModulResponseDTO> getAllOnlyModul2(String uuidRoles) {
        List<ListMenuOnlyModulResponseDTO> listMenuOnlyModulResponseDTOs = null;
        List<ListMenu> listMenus = listMenuRepository.findAll().stream().filter(onlyModul -> Objects.nonNull(onlyModul.getPathBackend())).collect(Collectors.toList());

        if (!listMenus.isEmpty()) {
            listMenuOnlyModulResponseDTOs = new ArrayList<>();
            for (ListMenu listMenu : listMenus) {

                Optional<ApplicationRolesMenus> applicationRolesMenusOpt = applicationRolesMenusRepository.findByRoleAndMenu(uuidRoles, listMenu.getId());

                ApplicationRolesMenusResponseDTO applicationRolesMenusResponseDTO = null;
                if (applicationRolesMenusOpt.isPresent()) {
                    applicationRolesMenusResponseDTO = new ApplicationRolesMenusResponseDTO(applicationRolesMenusOpt.get());
                } else {
                    applicationRolesMenusResponseDTO = new ApplicationRolesMenusResponseDTO();
                    applicationRolesMenusResponseDTO.setCanDownload(false);
                    applicationRolesMenusResponseDTO.setCanRead(false);
                    applicationRolesMenusResponseDTO.setCanRegenerate(false);
                    applicationRolesMenusResponseDTO.setCanUpdate(false);
                    applicationRolesMenusResponseDTO.setIsAdmin(false);
                    applicationRolesMenusResponseDTO.setCanUpload(false);
                    applicationRolesMenusResponseDTO.setMenuId(listMenu.getId());
                }

                ListMenuOnlyModulResponseDTO listMenuOnlyModulResponseDTO = new ListMenuOnlyModulResponseDTO(listMenu);

                listMenuOnlyModulResponseDTO.setPermissionList(applicationRolesMenusResponseDTO);
                listMenuOnlyModulResponseDTOs.add(listMenuOnlyModulResponseDTO);
            }
        }

        return listMenuOnlyModulResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ListMenuResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ListMenu> dataTablesOutput = listMenuRepository.findAll(dataTablesInput);

        List<ListMenuResponseDTO> listMenuResponseDTOs = new ArrayList<>();

        for (ListMenu listMenu : dataTablesOutput.getData()) {
            ListMenuResponseDTO listMenuResponseDTO = new ListMenuResponseDTO(listMenu);
            listMenuResponseDTO.setChildrens(listMenu.getChildrens());

            listMenuResponseDTOs.add(listMenuResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(listMenuResponseDTOs);

        return result;
    }

    public ListMenuResponseDTO findById(Long id) {
        ListMenuResponseDTO listMenuResponseDTO = null;
        Optional<ListMenu> listMenuOpt = listMenuRepository.findById(id);
        if (listMenuOpt.isPresent()) {
            ListMenu listMenu = listMenuOpt.get();

            listMenuResponseDTO = new ListMenuResponseDTO(listMenu);
        }

        return listMenuResponseDTO;

    }

    public void deleteById(Long id) {
        Optional<ListMenu> listMenuOpt = listMenuRepository.findById(id);
        if (listMenuOpt.isPresent()) {
            listMenuRepository.deleteById(id);
            LOGGER.info("Sucessfully delete ListMenu with id: " + id);
        } else {
            LOGGER.info("Cannot find ListMenu with id: " + id);
        }
    }

    public List<Select2ResponseDTO> getAllSelect2ForUpload(Select2RequestDTO select2RequestDTO) {
        List<ListMenu> listMenus = new ArrayList<ListMenu>();
        PageRequest pageRequest = PageRequest.of(select2RequestDTO.getPage(), 5);

        if (select2RequestDTO.getSearch().getText() != null) {
            if (select2RequestDTO.getSearch().getText().equals("")) {
                listMenus = listMenuRepository.findAll(pageRequest).getContent().stream().filter(isTable -> isTable.isIsTable()).collect(Collectors.toList());
            } else {
                listMenus = listMenuRepository.findByNamaMenuContaining(select2RequestDTO.getSearch().getText(), pageRequest).stream().filter(isTable -> isTable.isIsTable()).collect(Collectors.toList());
            }
        } else {
            listMenus = listMenuRepository.findAll(pageRequest).getContent().stream().filter(isTable -> isTable.isIsTable()).collect(Collectors.toList());
        }

        List<Select2ResponseDTO> select2ResponseDTOs = new ArrayList<Select2ResponseDTO>();
        for (ListMenu listMenu : listMenus) {
            select2ResponseDTOs.add(listMenu.toSelect2ResponseDTO());
        }

        return select2ResponseDTOs;
    }

    public List<Select2ResponseDTO> getAllSelect2ForMenu(Select2RequestDTO select2RequestDTO) {
        List<ListMenu> listMenus = new ArrayList<ListMenu>();
        PageRequest pageRequest = PageRequest.of(select2RequestDTO.getPage(), 5);

        if (select2RequestDTO.getSearch().getText() != null) {
            if (select2RequestDTO.getSearch().getText().equals("")) {
                listMenus = listMenuRepository.findAll(pageRequest).getContent();
            } else {
                listMenus = listMenuRepository.findByNamaMenuContaining(select2RequestDTO.getSearch().getText(), pageRequest);
            }
        } else {
            listMenus = listMenuRepository.findAll(pageRequest).getContent();
        }

        List<Select2ResponseDTO> select2ResponseDTOs = new ArrayList<Select2ResponseDTO>();
        for (ListMenu listMenu : listMenus) {
            select2ResponseDTOs.add(listMenu.toSelect2ResponseDTO());
        }

        return select2ResponseDTOs;
    }

    public List<Select2ResponseDTO> getAllSelect2ForRole(Select2RequestDTO select2RequestDTO) {
        List<ListMenu> listMenus = new ArrayList<ListMenu>();
        PageRequest pageRequest = PageRequest.of(select2RequestDTO.getPage(), 5);

        if (select2RequestDTO.getSearch().getText() != null) {
            if (select2RequestDTO.getSearch().getText().equals("")) {
                listMenus = listMenuRepository.findAll(pageRequest).getContent().stream().filter(role -> Objects.nonNull(role.getPathBackend())).collect(Collectors.toList());
            } else {
                listMenus = listMenuRepository.findByNamaMenuContaining(select2RequestDTO.getSearch().getText(), pageRequest).stream().filter(role -> Objects.nonNull(role.getPathBackend())).collect(Collectors.toList());
            }
        } else {
            listMenus = listMenuRepository.findAll(pageRequest).getContent().stream().filter(role -> Objects.nonNull(role.getPathBackend())).collect(Collectors.toList());
        }

        List<Select2ResponseDTO> select2ResponseDTOs = new ArrayList<Select2ResponseDTO>();
        for (ListMenu listMenu : listMenus) {
            select2ResponseDTOs.add(listMenu.toSelect2ResponseDTO());
        }

        return select2ResponseDTOs;
    }

}
