package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.IrrevocableLcResponseDTO;
import id.co.telkomsigma.portalapps.model.IrrevocableLc;
import id.co.telkomsigma.portalapps.repository.IrrevocableLcRepository;
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
public class IrrevocableLcService {
    public static final Logger LOGGER = LoggerFactory.getLogger(IrrevocableLcService.class);
    
    IrrevocableLcRepository irrevocableLcRepository;

    public IrrevocableLcService(IrrevocableLcRepository irrevocableLcRepository) {
        this.irrevocableLcRepository = irrevocableLcRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<IrrevocableLcResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<IrrevocableLc> dataTablesOutput = irrevocableLcRepository.findAll(dataTablesInput);

        List<IrrevocableLcResponseDTO> irrevocableLcResponseDTOs = new ArrayList<>();
        for (IrrevocableLc irrevocableLc : dataTablesOutput.getData()) {
            IrrevocableLcResponseDTO irrevocableLcResponseDTO = new IrrevocableLcResponseDTO(irrevocableLc);

            irrevocableLcResponseDTOs.add(irrevocableLcResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(irrevocableLcResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<IrrevocableLc> irrevocableLcs = irrevocableLcRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.irrevocableLcToExcel(irrevocableLcs);
        return in;
    }
    
}
