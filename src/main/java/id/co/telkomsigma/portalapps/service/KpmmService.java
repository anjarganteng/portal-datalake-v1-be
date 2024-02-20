package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.CoaBotResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.KpmmResponseDTO;
import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.portalapps.model.Kpmm;
import id.co.telkomsigma.portalapps.repository.KpmmRepository;
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
public class KpmmService {
    public static final Logger LOGGER = LoggerFactory.getLogger(KpmmService.class);
    
    KpmmRepository kpmmRepository;

    public KpmmService(KpmmRepository kpmmRepository) {
        this.kpmmRepository = kpmmRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<KpmmResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Kpmm> dataTablesOutput = kpmmRepository.findAll(dataTablesInput);

        List<KpmmResponseDTO> kpmmResponseDTOS = new ArrayList<>();
        for (Kpmm kpmm : dataTablesOutput.getData()) {
            KpmmResponseDTO kpmmResponseDTO = new KpmmResponseDTO(kpmm);

            kpmmResponseDTOS.add(kpmmResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(kpmmResponseDTOS);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<Kpmm> kpmms = kpmmRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.kpmmToExcel(kpmms);
        return in;
    }
    
    
}
