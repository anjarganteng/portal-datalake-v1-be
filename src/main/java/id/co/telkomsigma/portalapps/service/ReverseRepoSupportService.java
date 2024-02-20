package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.ReverseRepoSupportResponseDTO;
import id.co.telkomsigma.portalapps.model.ReverseRepoSupport;
import id.co.telkomsigma.portalapps.repository.ReverseRepoSupportRepository;
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
public class ReverseRepoSupportService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReverseRepoSupportService.class);
    
    ReverseRepoSupportRepository reverseRepoSupportRepository;

    public ReverseRepoSupportService(ReverseRepoSupportRepository reverseRepoSupportRepository) {
        this.reverseRepoSupportRepository = reverseRepoSupportRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ReverseRepoSupportResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ReverseRepoSupport> dataTablesOutput = reverseRepoSupportRepository.findAll(dataTablesInput);

        List<ReverseRepoSupportResponseDTO> reverseRepoSupportResponseDTOS = new ArrayList<>();
        for (ReverseRepoSupport reverseRepoSupport : dataTablesOutput.getData()) {
            ReverseRepoSupportResponseDTO reverseRepoSupportResponseDTO = new ReverseRepoSupportResponseDTO(reverseRepoSupport);

            reverseRepoSupportResponseDTOS.add(reverseRepoSupportResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(reverseRepoSupportResponseDTOS);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<ReverseRepoSupport> reverseRepoSupports = reverseRepoSupportRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.reverseRepoSupportsToExcel(reverseRepoSupports);
        return in;
    }
    
    
    
}
