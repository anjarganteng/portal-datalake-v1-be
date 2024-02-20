package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.monitorantasena.service.ProsesGenerateService;
import id.co.telkomsigma.portalapps.dto.request.AsetAntarKantorRequestDTO;
import id.co.telkomsigma.portalapps.service.AsetAntarKantorService;
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
@RequestMapping(value = Constant.Path.ASET_ANTAR_KANTOR)
public class AsetAntarKantorController {

    public Environment environment;
    public AsetAntarKantorService asetAntarKantorService;
    public ProsesGenerateService prosesGenerateService;

    @Autowired
    public AsetAntarKantorController(
            Environment environment,
            AsetAntarKantorService asetAntarKantorService,
            ProsesGenerateService prosesGenerateService
    ) {
        this.environment = environment;
        this.asetAntarKantorService = asetAntarKantorService;
        this.prosesGenerateService = prosesGenerateService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody AsetAntarKantorRequestDTO asetAntarKantorRequestDTO) {
        try {
            asetAntarKantorService.save(asetAntarKantorRequestDTO);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (DuplicateDataException dde) {
            dde.printStackTrace();

            return new ResponseEntity<>(Response.failed(dde.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.GET_ALL)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(Response.success(asetAntarKantorService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(asetAntarKantorService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "id") String id) {
        try {

            return new ResponseEntity<>(Response.success(asetAntarKantorService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") String id) {
        try {
            asetAntarKantorService.deleteById(id);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'download')")
    @GetMapping(value = Constant.Path.DOWNLOAD_EXCEL)
    public ResponseEntity<Resource> getFile() {
        String fileName = "Aset Antar Kantor.xlsx";
        InputStreamResource file = new InputStreamResource(asetAntarKantorService.loadToExcel());

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
