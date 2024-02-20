package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.PostEmploymentBenefitsResponseDTO;
import id.co.telkomsigma.portalapps.model.PostEmploymentBenefits;
import id.co.telkomsigma.portalapps.repository.PostEmploymentBenefitsRepository;
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
public class PostEmploymentBenefitsService {
    public static final Logger LOGGER = LoggerFactory.getLogger(PostEmploymentBenefitsService.class);
    
    PostEmploymentBenefitsRepository benefitsRepository;

    public PostEmploymentBenefitsService(PostEmploymentBenefitsRepository benefitsRepository) {
        this.benefitsRepository = benefitsRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PostEmploymentBenefitsResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<PostEmploymentBenefits> dataTablesOutput = benefitsRepository.findAll(dataTablesInput);

        List<PostEmploymentBenefitsResponseDTO> benefitsResponseDTOs = new ArrayList<>();
        for (PostEmploymentBenefits benefits : dataTablesOutput.getData()) {
            PostEmploymentBenefitsResponseDTO benefitsResponseDTO = new PostEmploymentBenefitsResponseDTO(benefits);

            benefitsResponseDTOs.add(benefitsResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(benefitsResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<PostEmploymentBenefits> benefitses = benefitsRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.postEmployeeBenefitsToExcel(benefitses);
        return in;
    }
    
    
    
    
}
