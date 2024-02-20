package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.SecuritiesResponseDTO;
import id.co.telkomsigma.portalapps.model.Securities;
import id.co.telkomsigma.portalapps.repository.SecuritiesRepository;
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
public class SecuritiesService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SecuritiesService.class);
    
    SecuritiesRepository securitiesRepository;

    public SecuritiesService(SecuritiesRepository securitiesRepository) {
        this.securitiesRepository = securitiesRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<SecuritiesResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Securities> dataTablesOutput = securitiesRepository.findAll(dataTablesInput);

        List<SecuritiesResponseDTO> securitiesResponseDTOs = new ArrayList<>();
        for (Securities securities : dataTablesOutput.getData()) {
            SecuritiesResponseDTO securitiesResponseDTO = new SecuritiesResponseDTO(securities);

            securitiesResponseDTOs.add(securitiesResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(securitiesResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<Securities> securitieses = securitiesRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.securitiesToExcel(securitieses);
        return in;
    }
    
    
    
}
