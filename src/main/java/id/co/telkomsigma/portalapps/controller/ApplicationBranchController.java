package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.dto.request.ApplicationBranchRequestOriginalDTO;
import id.co.telkomsigma.portalapps.dto.request.Select2RequestDTO;
import id.co.telkomsigma.portalapps.service.ApplicationBranchService;
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
@RequestMapping(value = Constant.Path.APPLICATION_BRANCH)
public class ApplicationBranchController {

    public Environment environment;
    public ApplicationBranchService applicationBranchService;

    @Autowired
    public ApplicationBranchController(
            Environment environment,
            ApplicationBranchService applicationBranchService
    ) {
        this.environment = environment;
        this.applicationBranchService = applicationBranchService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody ApplicationBranchRequestOriginalDTO requestDTO) {
        try {
            applicationBranchService.save(requestDTO);

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
            return new ResponseEntity<>(Response.success(applicationBranchService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(applicationBranchService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "uuid_branch") String uuidBranch) {
        try {
            return new ResponseEntity<>(Response.success(applicationBranchService.findById(uuidBranch)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "uuid_branch") String uuidBranch) {
        try {
            applicationBranchService.deleteById(uuidBranch);

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
            return new ResponseEntity<>(Response.success(applicationBranchService.getAllSelect2(select2RequestDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
