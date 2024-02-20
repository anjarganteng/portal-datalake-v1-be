package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.dto.request.ListMenuRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.Select2RequestDTO;
import id.co.telkomsigma.portalapps.service.ListMenuService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author satiya
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constant.Path.LIST_MENU)
public class ListMenuController {

    public Environment environment;
    public ListMenuService listMenuService;

    @Autowired
    public ListMenuController(
            Environment environment,
            ListMenuService listMenuService
    ) {
        this.environment = environment;
        this.listMenuService = listMenuService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody ListMenuRequestDTO listMenuRequestDTO) {
        try {
            listMenuService.save(listMenuRequestDTO);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.GET_ALL)
    public ResponseEntity<?> getAll(@RequestParam("form") String form) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.getAll(form)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.GET_ALL_ONLY_MODUL)
    public ResponseEntity<?> getAllOnlyModul(@RequestParam("uuid_roles") String uuidRoles) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.getAllOnlyModul2(uuidRoles)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "id") Long id) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") Long id) {
        try {
            listMenuService.deleteById(id);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (DataIntegrityViolationException die) {
            die.printStackTrace();

            if (die.getRootCause().getMessage().contains("foreign key")) {
                return new ResponseEntity<>(Response.failed(Constant.ResponseMessage.FAILED_DELETE_CONSTRAINT), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.GET_ALL_SELECT2_UPLOAD)
    public ResponseEntity<?> getAllSelect2ForUpload(@RequestBody Select2RequestDTO select2RequestDTO) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.getAllSelect2ForUpload(select2RequestDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.GET_ALL_SELECT2_MENU)
    public ResponseEntity<?> getAllSelect2ForMenu(@RequestBody Select2RequestDTO select2RequestDTO) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.getAllSelect2ForMenu(select2RequestDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.GET_ALL_SELECT2_ROLE)
    public ResponseEntity<?> getAllSelect2ForRole(@RequestBody Select2RequestDTO select2RequestDTO) {
        try {
            return new ResponseEntity<>(Response.success(listMenuService.getAllSelect2ForRole(select2RequestDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
