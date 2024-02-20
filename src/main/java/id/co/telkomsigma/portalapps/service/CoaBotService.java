package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.CoaBotResponseDTO;
import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.portalapps.repository.CoaBotRepository;
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
public class CoaBotService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CoaBotService.class);
    
    CoaBotRepository coaBotRepository;

    public CoaBotService(CoaBotRepository coaBotRepository) {
        this.coaBotRepository = coaBotRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CoaBotResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<CoaBot> dataTablesOutput = coaBotRepository.findAll(dataTablesInput);

        List<CoaBotResponseDTO> coaBotResponseDTOS = new ArrayList<>();
        for (CoaBot coaBot : dataTablesOutput.getData()) {
            CoaBotResponseDTO coaBotResponseDTO = new CoaBotResponseDTO(coaBot);

            coaBotResponseDTOS.add(coaBotResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(coaBotResponseDTOS);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<CoaBot> coaBots = coaBotRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.coaBotToExcel(coaBots);
        return in;
    }
    
}
