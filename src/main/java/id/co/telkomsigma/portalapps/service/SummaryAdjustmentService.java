package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.SummaryAdjustmentResponseDTO;
import id.co.telkomsigma.portalapps.model.SummaryAdjustment;
import id.co.telkomsigma.portalapps.repository.SummaryAdjustmentRepository;
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
public class SummaryAdjustmentService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SummaryAdjustmentService.class);
    
    private SummaryAdjustmentRepository summaryAdjustmentRepository;
    
    @Autowired
    public SummaryAdjustmentService(SummaryAdjustmentRepository summaryAdjustmentRepository) {
        this.summaryAdjustmentRepository = summaryAdjustmentRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<SummaryAdjustmentResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<SummaryAdjustment> dataTablesOutput = summaryAdjustmentRepository.findAll(dataTablesInput);

        List<SummaryAdjustmentResponseDTO> summaryAdjustmentResponseDTOS = new ArrayList<>();
        for (SummaryAdjustment summaryAdjustment : dataTablesOutput.getData()) {
            SummaryAdjustmentResponseDTO summaryAdjustmentResponseDTO = new SummaryAdjustmentResponseDTO(summaryAdjustment);

            summaryAdjustmentResponseDTOS.add(summaryAdjustmentResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(summaryAdjustmentResponseDTOS);

        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<SummaryAdjustment> summaryAdjustments = summaryAdjustmentRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.summaryAdjustmentToExcel(summaryAdjustments);
        return in;
    }
    
    
}
