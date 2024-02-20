package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.dto.request.ApplicationUsersRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.LoginRequestDTO;
import id.co.telkomsigma.portalapps.service.ApplicationUsersService;
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
@RequestMapping(value = Constant.Path.APPLICATION_USERS)
public class ApplicationUsersController {

    public Environment environment;
    public ApplicationUsersService applicationUsersService;

    @Autowired
    public ApplicationUsersController(
            Environment environment,
            ApplicationUsersService applicationUsersService
    ) {
        this.environment = environment;
        this.applicationUsersService = applicationUsersService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody ApplicationUsersRequestDTO applicationUsersRequestDTO) {
        try {
            applicationUsersService.save(applicationUsersRequestDTO);

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
            return new ResponseEntity<>(Response.success(applicationUsersService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(applicationUsersService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "uuid_users") String uuidUsers) {
        try {
            return new ResponseEntity<>(Response.success(applicationUsersService.findById(uuidUsers)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "uuid_users") String uuidUsers) {
        try {
            applicationUsersService.deleteById(uuidUsers);

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

    @PostMapping(value = Constant.Path.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            return applicationUsersService.login(loginRequestDTO);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = Constant.Path.GET_CUSTOM_MENU)
    public ResponseEntity<?> menuCustom(@RequestParam(value = "email") String email) {
        try {
            return new ResponseEntity<>(Response.success(applicationUsersService.menuCustom(email)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
