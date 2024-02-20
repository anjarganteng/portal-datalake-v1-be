package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.BankGuaranteeResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.IrrevocableLcResponseDTO;
import id.co.telkomsigma.portalapps.model.BankGuarantee;
import id.co.telkomsigma.portalapps.model.IrrevocableLc;
import id.co.telkomsigma.portalapps.repository.BankGuaranteeRepository;
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
public class BankGuaranteeService {
    public static final Logger LOGGER = LoggerFactory.getLogger(BankGuaranteeService.class);
    
    BankGuaranteeRepository bankGuaranteeRepository;

    public BankGuaranteeService(BankGuaranteeRepository bankGuaranteeRepository) {
        this.bankGuaranteeRepository = bankGuaranteeRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<BankGuaranteeResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<BankGuarantee> dataTablesOutput = bankGuaranteeRepository.findAll(dataTablesInput);

        List<BankGuaranteeResponseDTO> bankGuaranteeResponseDTOs = new ArrayList<>();
        for (BankGuarantee bankGuarantee : dataTablesOutput.getData()) {
            BankGuaranteeResponseDTO bankGuaranteeResponseDTO = new BankGuaranteeResponseDTO(bankGuarantee);

            bankGuaranteeResponseDTOs.add(bankGuaranteeResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(bankGuaranteeResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<BankGuarantee> bankGuarantees = bankGuaranteeRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.bankGuaranteeToExcel(bankGuarantees);
        return in;
    }
    
}
