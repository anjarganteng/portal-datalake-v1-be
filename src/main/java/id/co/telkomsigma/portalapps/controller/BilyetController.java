package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.service.BilyetService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author radit
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constant.Path.BILYET)
public class BilyetController {
    private BilyetService bilyetService;

    public BilyetController(BilyetService bilyetService) {
        this.bilyetService = bilyetService;
    }
    
    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = Constant.Path.FIND_ALL)
    public ResponseEntity<?> findAll(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(bilyetService.findAll(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('is', 'download')")
    @GetMapping(value = Constant.Path.DOWNLOAD_EXCEL)
    public ResponseEntity<Resource> getFile() {
        String fileName = "Bilyet.xlsx";
        InputStreamResource file = new InputStreamResource(bilyetService.loadToExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }    

    @GetMapping(value = "/poke")
    public ResponseEntity<?>  getThreadSleepTest() {
        try {
            System.out.println("START threadsleeptest");
            Thread.sleep(300000);
            System.out.println("END threadsleeptest");
        } catch (Exception e) {
        }
        return new ResponseEntity<>(Response.success("SUCCESS"), HttpStatus.OK);
    }    
    
}
