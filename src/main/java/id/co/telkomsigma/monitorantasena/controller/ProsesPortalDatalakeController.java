package id.co.telkomsigma.monitorantasena.controller;

import id.co.telkomsigma.monitorantasena.service.ProsesPortalDatalakeService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
@RequestMapping(value = Constant.Path.PROSES_PORTAL_DATALAKE)
public class ProsesPortalDatalakeController {

    public Environment environment;
    public ProsesPortalDatalakeService prosesPortalDatalakeService;

    @Autowired
    public ProsesPortalDatalakeController(
            Environment environment,
            ProsesPortalDatalakeService prosesPortalDatalakeService
    ) {
        this.environment = environment;
        this.prosesPortalDatalakeService = prosesPortalDatalakeService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(prosesPortalDatalakeService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.PROCESS)
    public ResponseEntity<?> manualTrigger(@RequestParam(value = "namaProses") String namaProses) {
        try {
            prosesPortalDatalakeService.manualTrigger(namaProses);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
