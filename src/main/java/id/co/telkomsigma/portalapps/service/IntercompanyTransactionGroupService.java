package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.IntercompanyTransactionGroupResponseDTO;
import id.co.telkomsigma.portalapps.model.IntercompanyTransactionGroup;
import id.co.telkomsigma.portalapps.repository.IntercompanyTransactionGroupRepository;
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
public class IntercompanyTransactionGroupService {
    public static final Logger LOGGER = LoggerFactory.getLogger(IntercompanyTransactionGroupService.class);
    
    IntercompanyTransactionGroupRepository groupRepository;

    public IntercompanyTransactionGroupService(IntercompanyTransactionGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<IntercompanyTransactionGroupResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<IntercompanyTransactionGroup> dataTablesOutput = groupRepository.findAll(dataTablesInput);

        List<IntercompanyTransactionGroupResponseDTO> intercompanyTransactionGroupResponseDTOs = new ArrayList<>();
        for (IntercompanyTransactionGroup intercompanyTransactionGroup : dataTablesOutput.getData()) {
            IntercompanyTransactionGroupResponseDTO intercompanyTransactionGroupResponseDTO = new IntercompanyTransactionGroupResponseDTO(intercompanyTransactionGroup);

            intercompanyTransactionGroupResponseDTOs.add(intercompanyTransactionGroupResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(intercompanyTransactionGroupResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<IntercompanyTransactionGroup> intercompanyTransactionGroups = groupRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.intercompanyGroupToExcel(intercompanyTransactionGroups);
        return in;
    }
    
}
