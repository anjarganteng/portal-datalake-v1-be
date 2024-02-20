package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.WeselResponseDTO;
import id.co.telkomsigma.portalapps.model.Wesel;
import id.co.telkomsigma.portalapps.repository.WeselRepository;
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
public class WeselService {
    public static final Logger LOGGER = LoggerFactory.getLogger(WeselService.class);
    
    WeselRepository weselRepository;

    public WeselService(WeselRepository weselRepository) {
        this.weselRepository = weselRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<WeselResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Wesel> dataTablesOutput = weselRepository.findAll(dataTablesInput);

        List<WeselResponseDTO> weselResponseDTOS = new ArrayList<>();
        for (Wesel wesel : dataTablesOutput.getData()) {
            WeselResponseDTO weselResponseDTO = new WeselResponseDTO(wesel);

            weselResponseDTOS.add(weselResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(weselResponseDTOS);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<Wesel> wesels = weselRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.weselToExcel(wesels);
        return in;
    }
    
}
