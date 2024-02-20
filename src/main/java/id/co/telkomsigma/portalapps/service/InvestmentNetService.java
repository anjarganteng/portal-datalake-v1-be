package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.InvestmentNetResponseDTO;
import id.co.telkomsigma.portalapps.model.InvestmentNet;
import id.co.telkomsigma.portalapps.repository.InvestmentNetRepository;
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
public class InvestmentNetService {
    public static final Logger LOGGER = LoggerFactory.getLogger(InvestmentNetService.class);
    
    InvestmentNetRepository investmentNetRepository;

    public InvestmentNetService(InvestmentNetRepository investmentNetRepository) {
        this.investmentNetRepository = investmentNetRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<InvestmentNetResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<InvestmentNet> dataTablesOutput = investmentNetRepository.findAll(dataTablesInput);

        List<InvestmentNetResponseDTO> investmentNetResponseDTOs = new ArrayList<>();
        for (InvestmentNet investmentNet : dataTablesOutput.getData()) {
            InvestmentNetResponseDTO investmentNetResponseDTO = new InvestmentNetResponseDTO(investmentNet);

            investmentNetResponseDTOs.add(investmentNetResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(investmentNetResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<InvestmentNet> investmentNets = investmentNetRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.investmentNetToExcel(investmentNets);
        return in;
    }
    
}
