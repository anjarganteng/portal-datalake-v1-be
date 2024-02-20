/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.portalapps.controller;

import id.co.telkomsigma.portalapps.service.ExcelService;
import id.co.telkomsigma.portalapps.service.LogService;
import id.co.telkomsigma.util.Constant;
import id.co.telkomsigma.util.ExcelHelperUpload;
import id.co.telkomsigma.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author radit
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constant.Path.UPLOAD_BOT)
public class UploadBotController {
    public Environment environment;
    public ExcelService excelService;
    public LogService logService;

    @Autowired
    public UploadBotController(Environment environment, ExcelService excelService, LogService logService) {
        this.environment = environment;
        this.excelService = excelService;
        this.logService = logService;
    }
    
    @GetMapping(value = "/poke")
    public String poke() {
        return "Poked from port " + environment.getProperty("server.port");
    }

    @PreAuthorize("hasPermission('is', 'upload')")
    @PostMapping(value = Constant.Path.SAVE_EXCEL)
    public ResponseEntity<?> saveExcel(@RequestParam("file") MultipartFile file, @RequestParam("obj") String obj) {
        System.out.println("Call saveExcel --> "+obj);
        if (ExcelHelperUpload.hasExcelFormat(file)) {
            try {
                excelService.save(file, obj);
                excelService.saveLog("BERHASIL", file, obj, "bot");
                return new ResponseEntity<>(Response.success(), HttpStatus.OK);
            } catch (RuntimeException re) {
                excelService.saveLog("GAGAL", file, obj, "bot");
                return new ResponseEntity<>(Response.failed(re.getMessage()), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
                excelService.saveLog("GAGAL", file, obj, "bot");
                return new ResponseEntity<>(Response.failed("Could not upload the file"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        excelService.saveLog("GAGAL", file, obj, "bot");
        return new ResponseEntity<>(Response.failed("Please Upload an excel file!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasPermission('is', 'read')")
    @PostMapping(value = "/find-all-bot")
    public ResponseEntity<?> findAllBot(@RequestBody DataTablesInput dataTablesInput) {
        try {
            return new ResponseEntity<>(Response.success(logService.findAllBot(dataTablesInput)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Response.failed(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
