package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.CifToFiResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.CoaBotResponseDTO;
import id.co.telkomsigma.portalapps.model.CifToFi;
import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.portalapps.repository.CifToFiRepository;
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
public class CifToFiService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CifToFiService.class);
    
    CifToFiRepository cifToFiRepository;

    public CifToFiService(CifToFiRepository cifToFiRepository) {
        this.cifToFiRepository = cifToFiRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CifToFiResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<CifToFi> dataTablesOutput = cifToFiRepository.findAll(dataTablesInput);

        List<CifToFiResponseDTO> cifToFiResponseDTOS = new ArrayList<>();
        for (CifToFi cifToFi : dataTablesOutput.getData()) {
            CifToFiResponseDTO cifToFiResponseDTO = new CifToFiResponseDTO(cifToFi);

            cifToFiResponseDTOS.add(cifToFiResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(cifToFiResponseDTOS);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<CifToFi> cifToFis = cifToFiRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.cifToFiToExcel(cifToFis);
        return in;
    }
    
}
