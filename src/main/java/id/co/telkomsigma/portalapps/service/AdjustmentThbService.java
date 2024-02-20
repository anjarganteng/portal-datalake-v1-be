package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.AdjustmentThbResponseDTO;
import id.co.telkomsigma.portalapps.model.AdjustmentThb;
import id.co.telkomsigma.portalapps.repository.AdjustmentThbRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

/**
 *
 * @author radit
 */
@Service
public class AdjustmentThbService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentThbService.class);
    
    private AdjustmentThbRepository adjustmentThbRepository;

    @Autowired
    public AdjustmentThbService(AdjustmentThbRepository adjustmentThbRepository) {
        this.adjustmentThbRepository = adjustmentThbRepository;
    }
    

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<AdjustmentThbResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<AdjustmentThb> dataTablesOutput = adjustmentThbRepository.findAll(dataTablesInput);

        List<AdjustmentThbResponseDTO> adjustmentThbResponseDTOS = new ArrayList<>();
        for (AdjustmentThb adjustmentThb : dataTablesOutput.getData()) {
            AdjustmentThbResponseDTO adjustmentThbResponseDTO = new AdjustmentThbResponseDTO(adjustmentThb);

            adjustmentThbResponseDTOS.add(adjustmentThbResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(adjustmentThbResponseDTOS);

        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<AdjustmentThb> adjustmentThbs = adjustmentThbRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.adjustmentThbToExcel(adjustmentThbs);
        return in;
    }
    
    
    
}
