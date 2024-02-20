package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.CoaBotResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.ShortTermEmployeeBenefitsResponseDTO;
import id.co.telkomsigma.portalapps.model.CoaBot;
import id.co.telkomsigma.portalapps.model.ShortTermEmployeeBenefits;
import id.co.telkomsigma.portalapps.repository.ShortTermEmployeeBenefitsRepository;
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
public class ShortTermEmployeeBenefitsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ShortTermEmployeeBenefitsService.class);
    
    ShortTermEmployeeBenefitsRepository employeeBenefitsRepository;

    public ShortTermEmployeeBenefitsService(ShortTermEmployeeBenefitsRepository employeeBenefitsRepository) {
        this.employeeBenefitsRepository = employeeBenefitsRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ShortTermEmployeeBenefitsResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ShortTermEmployeeBenefits> dataTablesOutput = employeeBenefitsRepository.findAll(dataTablesInput);

        List<ShortTermEmployeeBenefitsResponseDTO> benefitsResponseDTOs = new ArrayList<>();
        for (ShortTermEmployeeBenefits employeeBenefits : dataTablesOutput.getData()) {
            ShortTermEmployeeBenefitsResponseDTO benefitsResponseDTO = new ShortTermEmployeeBenefitsResponseDTO(employeeBenefits);

            benefitsResponseDTOs.add(benefitsResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(benefitsResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<ShortTermEmployeeBenefits> employeeBenefitses = employeeBenefitsRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.shortTermEmployeeBenefitsToExcel(employeeBenefitses);
        return in;
    }
    
    
    
}
