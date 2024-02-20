package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.CoaBotResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.IntercompanyTransactionKbankResponseDTO;
import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.portalapps.model.IntercompanyTransactionKbank;
import id.co.telkomsigma.portalapps.repository.IntercompanyTransactionKbankRepository;
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
public class IntercompanyTransactionKbankService {
    public static final Logger LOGGER = LoggerFactory.getLogger(IntercompanyTransactionKbankService.class);
    
    IntercompanyTransactionKbankRepository intercompanyTransactionKbankRepository;

    public IntercompanyTransactionKbankService(IntercompanyTransactionKbankRepository intercompanyTransactionKbankRepository) {
        this.intercompanyTransactionKbankRepository = intercompanyTransactionKbankRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<IntercompanyTransactionKbankResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<IntercompanyTransactionKbank> dataTablesOutput = intercompanyTransactionKbankRepository.findAll(dataTablesInput);

        List<IntercompanyTransactionKbankResponseDTO> intercompanyTransactionKbankResponseDTOs = new ArrayList<>();
        for (IntercompanyTransactionKbank intercompanyTransactionKbank : dataTablesOutput.getData()) {
            IntercompanyTransactionKbankResponseDTO intercompanyTransactionKbankResponseDTO = new IntercompanyTransactionKbankResponseDTO(intercompanyTransactionKbank);

            intercompanyTransactionKbankResponseDTOs.add(intercompanyTransactionKbankResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(intercompanyTransactionKbankResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<IntercompanyTransactionKbank> intercompanyTransactionKbanks = intercompanyTransactionKbankRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.intercompanyKbankToExcel(intercompanyTransactionKbanks);
        return in;
    }
   
}
