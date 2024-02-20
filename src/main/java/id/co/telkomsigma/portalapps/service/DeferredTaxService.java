package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.CoaBotResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.DeferredTaxResponseDTO;
import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.portalapps.model.DeferredTax;
import id.co.telkomsigma.portalapps.repository.DeferredTaxRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

/**
 *
 * @author radit
 */
@Service
public class DeferredTaxService {
    public static final Logger LOGGER = LoggerFactory.getLogger(DeferredTaxService.class);
    
    DeferredTaxRepository repository;

    public DeferredTaxService(DeferredTaxRepository repository) {
        this.repository = repository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<DeferredTaxResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<DeferredTax> dataTablesOutput = repository.findAll(dataTablesInput);

        List<DeferredTaxResponseDTO> deferredTaxResponseDTOS = new ArrayList<>();
        for (DeferredTax deferredTax : dataTablesOutput.getData()) {
            DeferredTaxResponseDTO  deferredTaxResponseDTO= new DeferredTaxResponseDTO(deferredTax);

            deferredTaxResponseDTOS.add(deferredTaxResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(deferredTaxResponseDTOS);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<DeferredTax> deferredTaxs = repository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.deferredTaxToExcel(deferredTaxs);
        return in;
    }
    
}
