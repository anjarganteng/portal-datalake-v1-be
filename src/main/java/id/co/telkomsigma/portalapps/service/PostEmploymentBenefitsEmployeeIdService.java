package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.PostEmploymentBenefitsEmployeeIdResponseDTO;
import id.co.telkomsigma.portalapps.model.PostEmploymentBenefitsEmployeeId;
import id.co.telkomsigma.portalapps.repository.PostEmploymentBenefitsEmployeeIdRepository;
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
public class PostEmploymentBenefitsEmployeeIdService {
    public static final Logger LOGGER = LoggerFactory.getLogger(PostEmploymentBenefitsEmployeeIdService.class);
    
    PostEmploymentBenefitsEmployeeIdRepository benefitsEmployeeIdRepository;

    public PostEmploymentBenefitsEmployeeIdService(PostEmploymentBenefitsEmployeeIdRepository benefitsEmployeeIdRepository) {
        this.benefitsEmployeeIdRepository = benefitsEmployeeIdRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PostEmploymentBenefitsEmployeeIdResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<PostEmploymentBenefitsEmployeeId> dataTablesOutput = benefitsEmployeeIdRepository.findAll(dataTablesInput);

        List<PostEmploymentBenefitsEmployeeIdResponseDTO> benefitsEmployeeIdResponseDTOs = new ArrayList<>();
        for (PostEmploymentBenefitsEmployeeId benefitsEmployeeId : dataTablesOutput.getData()) {
            PostEmploymentBenefitsEmployeeIdResponseDTO benefitsEmployeeIdResponseDTO = new PostEmploymentBenefitsEmployeeIdResponseDTO(benefitsEmployeeId);

            benefitsEmployeeIdResponseDTOs.add(benefitsEmployeeIdResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(benefitsEmployeeIdResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<PostEmploymentBenefitsEmployeeId> benefitsEmployeeIds = benefitsEmployeeIdRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.postEmployeeBenefitsEmployeeIdToExcel(benefitsEmployeeIds);
        return in;
    }
    
}
