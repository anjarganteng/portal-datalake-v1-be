package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.OtherBenefitsPaidResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.WeselResponseDTO;
import id.co.telkomsigma.portalapps.model.OtherBenefitsPaid;
import id.co.telkomsigma.portalapps.model.Wesel;
import id.co.telkomsigma.portalapps.repository.OtherBenefitsPaidRepository;
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
public class OtherBenefitsPaidService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OtherBenefitsPaidService.class);
    
    OtherBenefitsPaidRepository repository;

    public OtherBenefitsPaidService(OtherBenefitsPaidRepository repository) {
        this.repository = repository;
    }
    
    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<OtherBenefitsPaidResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<OtherBenefitsPaid> dataTablesOutput = repository.findAll(dataTablesInput);

        List<OtherBenefitsPaidResponseDTO> otherBenefitsPaidResponseDTOs = new ArrayList<>();
        for (OtherBenefitsPaid otherBenefitsPaid : dataTablesOutput.getData()) {
            OtherBenefitsPaidResponseDTO otherBenefitsPaidResponseDTO = new OtherBenefitsPaidResponseDTO(otherBenefitsPaid);

            otherBenefitsPaidResponseDTOs.add(otherBenefitsPaidResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(otherBenefitsPaidResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<OtherBenefitsPaid> otherBenefitsPaids = repository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.otherBenefitsPaidToExcel(otherBenefitsPaids);
        return in;
    }
    
}
