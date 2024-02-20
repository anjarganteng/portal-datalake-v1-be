package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.ApplicationRolesSimpleRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.Select2RequestDTO;
import id.co.telkomsigma.portalapps.service.ApplicationRolesService;
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
@RequestMapping(value = Constant.Path.APPLICATION_ROLES)
public class ApplicationRolesController {

    public Environment environment;
    public ApplicationRolesService applicationRolesService;

    @Autowired
    public ApplicationRolesController(
            Environment environment,
            ApplicationRolesService applicationRolesService
    ) {
        this.environment = environment;
        this.applicationRolesService = applicationRolesService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody ApplicationRolesRequestDTO applicationRolesRequestDTO) {
        try {
            applicationRolesService.save(applicationRolesRequestDTO);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE_ROLE)
    public ResponseEntity<?> save(@RequestBody ApplicationRolesSimpleRequestDTO applicationRolesSimpleRequestDTO) {
        try {
            applicationRolesService.save(applicationRolesSimpleRequestDTO);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.GET_ALL)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(Response.success(applicationRolesService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(applicationRolesService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "uuid_roles") String uuidRoles) {
        try {
            return new ResponseEntity<>(Response.success(applicationRolesService.findById(uuidRoles)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "uuid_roles") String uuidRoles) {
        try {
            applicationRolesService.deleteById(uuidRoles);

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
    @PostMapping(value = Constant.Path.GET_ALL_SELECT2)
    public ResponseEntity<?> getAllSelect2(@RequestBody Select2RequestDTO select2RequestDTO) {
        try {
            return new ResponseEntity<>(Response.success(applicationRolesService.getAllSelect2(select2RequestDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
