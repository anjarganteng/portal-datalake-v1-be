package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.DuplicateidResponseDTO;
import id.co.telkomsigma.portalapps.model.Duplicateid;
import id.co.telkomsigma.portalapps.repository.DuplicateidRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author satiya
 */
@Service
public class DuplicateidService {

    public static final Logger LOGGER = LoggerFactory.getLogger(DuplicateidService.class);

    private DuplicateidRepository duplicateidRepository;

    @Autowired
    public DuplicateidService(
            DuplicateidRepository duplicateidRepository
    ) {
        this.duplicateidRepository = duplicateidRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<DuplicateidResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Duplicateid> dataTablesOutput = duplicateidRepository.findAll(dataTablesInput);

        List<DuplicateidResponseDTO> duplicateidResponseDTOS = new ArrayList<>();
        for (Duplicateid duplicateid : dataTablesOutput.getData()) {
            DuplicateidResponseDTO duplicateidResponseDTO = new DuplicateidResponseDTO(duplicateid);

            duplicateidResponseDTOS.add(duplicateidResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(duplicateidResponseDTOS);

        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<Duplicateid> duplicateids = duplicateidRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.duplicateCifToExcel(duplicateids);
        return in;
    }
}
