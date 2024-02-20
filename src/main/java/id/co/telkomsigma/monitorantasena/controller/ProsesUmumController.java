package id.co.telkomsigma.monitorantasena.controller;

import id.co.telkomsigma.monitorantasena.service.ProsesUmumService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = Constant.Path.TRIGGER_MANUAL)
public class ProsesUmumController {

    public Environment environment;
    public ProsesUmumService prosesUmumService;

    @Autowired
    public ProsesUmumController(
            Environment environment,
            ProsesUmumService prosesUmumService
    ) {
        this.environment = environment;
        this.prosesUmumService = prosesUmumService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(prosesUmumService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL_BOT)
    public ResponseEntity<?> findAllBot(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(prosesUmumService.findAllBot(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.PROCESS)
    public ResponseEntity<?> manualTrigger(@RequestParam(value = "namaProses") String namaProses) {
        try {
            prosesUmumService.manualTrigger(namaProses);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.PROCESS_BOT)
    public ResponseEntity<?> manualTriggerBot(@RequestParam(value = "namaProses") String namaProses) {
        try {
            prosesUmumService.manualTriggerBot(namaProses);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>("ss", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
