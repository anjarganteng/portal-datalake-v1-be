package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.MappingNcdRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.MappingNcdResponseDTO;
import id.co.telkomsigma.portalapps.model.MappingNcd;
import id.co.telkomsigma.portalapps.repository.MappingNcdRepository;
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
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class MappingNcdService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MappingNcdService.class);

    private MappingNcdRepository mappingNcdRepository;

    @Autowired
    public MappingNcdService(
            MappingNcdRepository mappingNcdRepository
    ) {
        this.mappingNcdRepository = mappingNcdRepository;
    }

    public void save(MappingNcdRequestDTO mappingNcdRequestDTO) {
        MappingNcd mappingNcd = null;
        Optional<MappingNcd> mappingNcdOpt = mappingNcdRepository.findById(mappingNcdRequestDTO.getNoDeposito());

        if (mappingNcdRequestDTO.isNew()) {
            //do insert
            if (mappingNcdOpt.isPresent()) {
                throw new DuplicateDataException("No Deposito you entered already exist!");
            } else {
                mappingNcd = new MappingNcd(mappingNcdRequestDTO);
            }
        } else {
            //do update
            mappingNcd = mappingNcdOpt.get();
            mappingNcd.setNoCif(mappingNcdRequestDTO.getNoCif());
        }

        MappingNcd saved = mappingNcdRepository.save(mappingNcd);
        LOGGER.info("Successfully save application with id: " + saved.getNoDeposito());
    }

    public List<MappingNcdResponseDTO> getAll() {
        List<MappingNcdResponseDTO> mappingNcdResponseDTOs = null;
        List<MappingNcd> mappingNcds = mappingNcdRepository.findAll();

        if (!mappingNcds.isEmpty()) {
            mappingNcdResponseDTOs = new ArrayList<>();
            for (MappingNcd mappingNcd : mappingNcds) {
                MappingNcdResponseDTO mappingNcdResponseDTO = new MappingNcdResponseDTO(mappingNcd);
                mappingNcdResponseDTOs.add(mappingNcdResponseDTO);
            }

        }

        return mappingNcdResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<MappingNcdResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<MappingNcd> dataTablesOutput = mappingNcdRepository.findAll(dataTablesInput);

        List<MappingNcdResponseDTO> mappingNcdResponseDTOS = new ArrayList<>();
        for (MappingNcd mappingNcd : dataTablesOutput.getData()) {
            MappingNcdResponseDTO mappingNcdResponseDTO = new MappingNcdResponseDTO(mappingNcd);

            mappingNcdResponseDTOS.add(mappingNcdResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(mappingNcdResponseDTOS);

        return result;
    }

    public MappingNcdResponseDTO findById(String id) {
        MappingNcdResponseDTO mappingNcdResponseDTO = null;
        Optional<MappingNcd> mappingNcdOpt = mappingNcdRepository.findById(id);
        if (mappingNcdOpt.isPresent()) {
            MappingNcd mappingNcd = mappingNcdOpt.get();

            mappingNcdResponseDTO = new MappingNcdResponseDTO(mappingNcd);
        }

        return mappingNcdResponseDTO;
    }

    public void deleteById(String id) {
        Optional<MappingNcd> mappingNcdOpt = mappingNcdRepository.findById(id);
        if (mappingNcdOpt.isPresent()) {
            mappingNcdRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<MappingNcd> mappingNcds = mappingNcdRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.mappingNcdToExcel(mappingNcds);
        return in;
    }
}
