package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.LogResponseDTO;
import id.co.telkomsigma.portalapps.model.ImportExcelLog;
import id.co.telkomsigma.portalapps.repository.ImportExcelLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import java.util.ArrayList;
import java.util.List;

/**
 * @author satiya
 */
@Service
public class LogService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    private ImportExcelLogRepository importExcelLogRepository;

    @Autowired
    public LogService(
            ImportExcelLogRepository importExcelLogRepository
    ) {
        this.importExcelLogRepository = importExcelLogRepository;
    }

    public List<LogResponseDTO> getAll() {
        List<LogResponseDTO> logResponseDTOs = null;
        List<ImportExcelLog> importExcelLogs = importExcelLogRepository.findAll();

        if (!importExcelLogs.isEmpty()) {
            logResponseDTOs = new ArrayList<>();
            for (ImportExcelLog importExcelLog : importExcelLogs) {
                LogResponseDTO logResponseDTO = new LogResponseDTO(importExcelLog);
                logResponseDTOs.add(logResponseDTO);
            }

        }

        return logResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<LogResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ImportExcelLog> dataTablesOutput = importExcelLogRepository.findAll(dataTablesInput);

        List<LogResponseDTO> logResponseDTOS = new ArrayList<>();
        for (ImportExcelLog importExcelLog : dataTablesOutput.getData()) {
            LogResponseDTO logResponseDTO = new LogResponseDTO(importExcelLog);

            logResponseDTOS.add(logResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(logResponseDTOS);

        return result;
    }

    public DataTablesOutput<?> findAllAntasena(DataTablesInput dataTablesInput) {
        Specification<ImportExcelLog> formSpecification = (Specification<ImportExcelLog>) (root, query, criteriaBuilder) -> {
            Expression<String> formExpression = criteriaBuilder.lower(root.get("form"));
            return criteriaBuilder.equal(formExpression, "antasena");
        };

        return importExcelLogRepository.findAll(dataTablesInput, formSpecification);
    }

    public DataTablesOutput<?> findAllLps(DataTablesInput dataTablesInput) {
        Specification<ImportExcelLog> formSpecification = (Specification<ImportExcelLog>) (root, query, criteriaBuilder) -> {
            Expression<String> formExpression = criteriaBuilder.lower(root.get("form"));
            return criteriaBuilder.equal(formExpression, "lps");
        };

        return importExcelLogRepository.findAll(dataTablesInput, formSpecification);
    }

    public DataTablesOutput<?> findAllParameter(DataTablesInput dataTablesInput) {
        Specification<ImportExcelLog> formSpecification = (Specification<ImportExcelLog>) (root, query, criteriaBuilder) -> {
            Expression<String> formExpression = criteriaBuilder.lower(root.get("form"));
            return criteriaBuilder.equal(formExpression, "parameter");
        };

        return importExcelLogRepository.findAll(dataTablesInput, formSpecification);
    }
    
    public DataTablesOutput<?> findAllBot(DataTablesInput dataTablesInput) {
        Specification<ImportExcelLog> formSpecification = (Specification<ImportExcelLog>) (root, query, criteriaBuilder) -> {
            Expression<String> formExpression = criteriaBuilder.lower(root.get("form"));
            return criteriaBuilder.equal(formExpression, "bot");
        };

        return importExcelLogRepository.findAll(dataTablesInput, formSpecification);
    }
}
