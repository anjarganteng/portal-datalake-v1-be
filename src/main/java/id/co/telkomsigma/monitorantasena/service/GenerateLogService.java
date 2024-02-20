package id.co.telkomsigma.monitorantasena.service;

import id.co.telkomsigma.monitorantasena.model.GenerateLog;
import id.co.telkomsigma.monitorantasena.repository.GenerateLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;

/**
 * @author satiya
 */
@Service
public class GenerateLogService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GenerateLogService.class);

    private GenerateLogRepository generateLogRepository;

    public GenerateLogService(
            GenerateLogRepository generateLogRepository
    ) {
        this.generateLogRepository = generateLogRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        Specification<GenerateLog> processNameSpecification = (Specification<GenerateLog>) (root, query, criteriaBuilder) -> {
            Expression<String> processNameExpression = criteriaBuilder.lower(root.get("processName"));
            return criteriaBuilder.equal(processNameExpression, "REGENERATE");
        };

        return generateLogRepository.findAll(dataTablesInput, processNameSpecification);
    }
}
