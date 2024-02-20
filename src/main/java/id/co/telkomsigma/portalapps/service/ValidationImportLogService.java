package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.ValidationImportLogResponseDTO;
import id.co.telkomsigma.portalapps.model.ValidationImportLog;
import id.co.telkomsigma.portalapps.repository.ValidationImportLogRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author satiya
 */
@Service
public class ValidationImportLogService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ValidationImportLogService.class);

    private ValidationImportLogRepository validationImportLogRepository;

    @Autowired
    public ValidationImportLogService(
            ValidationImportLogRepository validationImportLogRepository
    ) {
        this.validationImportLogRepository = validationImportLogRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ValidationImportLogResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ValidationImportLog> dataTablesOutput = validationImportLogRepository.findAll(dataTablesInput);

        List<ValidationImportLogResponseDTO> logResponseDTOS = new ArrayList<>();
        for (ValidationImportLog validationImportLog : dataTablesOutput.getData()) {
            ValidationImportLogResponseDTO validationImportLogResponseDTO = new ValidationImportLogResponseDTO(validationImportLog);

            logResponseDTOS.add(validationImportLogResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(logResponseDTOS);

        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<ValidationImportLog> validationImportLogs = validationImportLogRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.validationLogToExcel(validationImportLogs);
        return in;
    }
}
