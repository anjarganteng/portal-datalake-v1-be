package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.monitorantasena.service.ProsesGenerateService;
import id.co.telkomsigma.portalapps.dto.request.PpaRequestDTO;
import id.co.telkomsigma.portalapps.service.PpaService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author satiya
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constant.Path.PPA)
public class PpaController {

    public Environment environment;
    public PpaService ppaService;
    public ProsesGenerateService prosesGenerateService;

    @Autowired
    public PpaController(
            Environment environment,
            PpaService ppaService,
            ProsesGenerateService prosesGenerateService
    ) {
        this.environment = environment;
        this.ppaService = ppaService;
        this.prosesGenerateService = prosesGenerateService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody PpaRequestDTO ppaRequestDTO) {
        try {
            ppaService.save(ppaRequestDTO);

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
            return new ResponseEntity<>(Response.success(ppaService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(ppaService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "id") String id,
                                      @RequestParam(value = "id2") String id2,
                                      @RequestParam(value = "id3") String id3,
                                      @RequestParam(value = "id4") String id4,
                                      @RequestParam(value = "id5") String id5) {
        try {

            return new ResponseEntity<>(Response.success(ppaService.findById(id, id2, id3, id4, id5)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") String id,
                                        @RequestParam(value = "id2") String id2,
                                        @RequestParam(value = "id3") String id3,
                                        @RequestParam(value = "id4") String id4,
                                        @RequestParam(value = "id5") String id5) {
        try {
            ppaService.deleteById(id, id2, id3, id4, id5);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'download')")
    @GetMapping(value = Constant.Path.DOWNLOAD_EXCEL)
    public ResponseEntity<Resource> getFile() {
        String fileName = "Ppa.xlsx";
        InputStreamResource file = new InputStreamResource(ppaService.loadToExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }

    @PreAuthorize("hasPermission('is', 'regenerate')")
    @PostMapping(value = Constant.Path.REGENERATE_DATA)
    public ResponseEntity<?> regenerateData(@RequestParam(value = "namaProses") String namaProses) {
        try {
            prosesGenerateService.regenerateData(namaProses);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

