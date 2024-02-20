package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.service.SandiService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author satiya
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constant.Path.SANDI)
public class SandiController {

    private SandiService sandiService;

    @Autowired
    public SandiController(
            SandiService sandiService
    ) {
        this.sandiService = sandiService;
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.GET_ALL)
    public ResponseEntity<?> getAll(@RequestParam(value = "tipe_sandi") String tipeSandi) {
        try {
            return new ResponseEntity<>(Response.success(sandiService.getAll(tipeSandi)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
