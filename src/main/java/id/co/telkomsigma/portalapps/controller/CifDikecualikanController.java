package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.dto.request.CifDikecualikanRequestDTO;
import id.co.telkomsigma.portalapps.service.CifDikecualikanService;
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
@RequestMapping(value = Constant.Path.CIF_DIKECUALIKAN)
public class CifDikecualikanController {

    public Environment environment;
    public CifDikecualikanService cifDikecualikanService;

    @Autowired
    public CifDikecualikanController(
            Environment environment,
            CifDikecualikanService cifDikecualikanService
    ) {
        this.environment = environment;
        this.cifDikecualikanService = cifDikecualikanService;
    }

    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.SAVE)
    public ResponseEntity<?> save(@RequestBody CifDikecualikanRequestDTO cifDikecualikanRequestDTO) {
        try {
            cifDikecualikanService.save(cifDikecualikanRequestDTO);

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
            return new ResponseEntity<>(Response.success(cifDikecualikanService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(cifDikecualikanService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @GetMapping(value = Constant.Path.FIND_BY_ID)
    public ResponseEntity<?> findById(@RequestParam(value = "id") String id) {
        try {

            return new ResponseEntity<>(Response.success(cifDikecualikanService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'update')")
    @PostMapping(value = Constant.Path.DELETE_BY_ID)
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") String id) {
        try {
            cifDikecualikanService.deleteById(id);

            return new ResponseEntity<>(Response.success(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'download')")
    @GetMapping(value = Constant.Path.DOWNLOAD_EXCEL)
    public ResponseEntity<Resource> getFile() {
        String fileName = "Cif Dikecualikan.xlsx";
        InputStreamResource file = new InputStreamResource(cifDikecualikanService.loadToExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }
}
