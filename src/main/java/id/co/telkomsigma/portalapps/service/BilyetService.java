package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.BilyetResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.SummaryAdjustmentResponseDTO;
import id.co.telkomsigma.portalapps.model.Bilyet;
import id.co.telkomsigma.portalapps.model.SummaryAdjustment;
import id.co.telkomsigma.portalapps.repository.BilyetRepository;
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
public class BilyetService {
    public static final Logger LOGGER = LoggerFactory.getLogger(BilyetService.class);
    
    BilyetRepository bilyetRepository;

    public BilyetService(BilyetRepository bilyetRepository) {
        this.bilyetRepository = bilyetRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<BilyetResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Bilyet> dataTablesOutput = bilyetRepository.findAll(dataTablesInput);

        List<BilyetResponseDTO> bilyetResponseDTOS = new ArrayList<>();
        for (Bilyet bilyet : dataTablesOutput.getData()) {
            BilyetResponseDTO bilyetResponseDTO = new BilyetResponseDTO(bilyet);

            bilyetResponseDTOS.add(bilyetResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(bilyetResponseDTOS);

        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<Bilyet> bilyets = bilyetRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.bilyetToExcel(bilyets);
        return in;
    }
    
}
