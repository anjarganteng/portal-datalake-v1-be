package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.FundingConcentrationInterbankResponseDTO;
import id.co.telkomsigma.portalapps.model.FundingConcentrationInterbank;
import id.co.telkomsigma.portalapps.repository.FundingConcentrationInterbankRepository;
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
public class FundingConcentrationInterbankService {
    public static final Logger LOGGER = LoggerFactory.getLogger(FundingConcentrationInterbankService.class);
    
    FundingConcentrationInterbankRepository repository;

    public FundingConcentrationInterbankService(FundingConcentrationInterbankRepository repository) {
        this.repository = repository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<FundingConcentrationInterbankResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<FundingConcentrationInterbank> dataTablesOutput = repository.findAll(dataTablesInput);

        List<FundingConcentrationInterbankResponseDTO> dtos = new ArrayList<>();
        for (FundingConcentrationInterbank fundingConcentrationInterbank : dataTablesOutput.getData()) {
            FundingConcentrationInterbankResponseDTO dto = new FundingConcentrationInterbankResponseDTO(fundingConcentrationInterbank);

            dtos.add(dto);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(dtos);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<FundingConcentrationInterbank> fundingConcentrationInterbanks = repository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.fundingConcentrationInterbankToExcel(fundingConcentrationInterbanks);
        return in;
    }
    
    
    
    
}
