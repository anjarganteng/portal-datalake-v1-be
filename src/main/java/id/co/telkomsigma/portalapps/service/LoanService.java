package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.LoanResponseDTO;
import id.co.telkomsigma.portalapps.model.Loan;
import id.co.telkomsigma.portalapps.repository.LoanRepository;
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
public class LoanService {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);
    
    LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<LoanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Loan> dataTablesOutput = loanRepository.findAll(dataTablesInput);

        List<LoanResponseDTO> loanResponseDTOS = new ArrayList<>();
        for (Loan loan : dataTablesOutput.getData()) {
            LoanResponseDTO loanResponseDTO = new LoanResponseDTO(loan);

            loanResponseDTOS.add(loanResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(loanResponseDTOS);

        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<Loan> loans = loanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.loanToExcel(loans);
        return in;
    }
    
}
