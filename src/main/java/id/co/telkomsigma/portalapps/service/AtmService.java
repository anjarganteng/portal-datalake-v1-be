package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.AtmRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.AtmResponseDTO;
import id.co.telkomsigma.portalapps.model.Atm;
import id.co.telkomsigma.portalapps.repository.AtmRepository;
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
public class AtmService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AtmService.class);

    private AtmRepository atmRepository;

    @Autowired
    public AtmService(
            AtmRepository atmRepository
    ) {
        this.atmRepository = atmRepository;
    }

    public void save(AtmRequestDTO atmRequestDTO) {
        Atm atm = null;
        Optional<Atm> atmOpt = atmRepository.findById(atmRequestDTO.getId());

        if (atmRequestDTO.isNew()) {
            // do insert
            if (atmOpt.isPresent()) {
                throw new DuplicateDataException("Id you entered already exist!");
            } else {
                atm = new Atm(atmRequestDTO);
            }
        } else {
            // do update
            atm = atmOpt.get();
            atm.setJenis(atmRequestDTO.getJenis());
            atm.setStatus(atmRequestDTO.getStatus());
            atm.setLokasi(atmRequestDTO.getLokasi());
            atm.setUsaha(atmRequestDTO.getUsaha());
            atm.setKeterangan(atmRequestDTO.getKeterangan());
        }

        atmRepository.save(atm);
        LOGGER.info("Successfully save application with id: " + atmRequestDTO.getId());
    }

    public List<AtmResponseDTO> getAll() {
        List<AtmResponseDTO> atmResponseDTOs = null;
        List<Atm> atms = atmRepository.findAll();

        if (!atms.isEmpty()) {
            atmResponseDTOs = new ArrayList<>();
            for (Atm atm : atms) {
                AtmResponseDTO atmResponseDTO = new AtmResponseDTO(atm);
                atmResponseDTOs.add(atmResponseDTO);
            }

        }

        return atmResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<AtmResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Atm> dataTablesOutput = atmRepository.findAll(dataTablesInput);

        List<AtmResponseDTO> atmResponseDTOS = new ArrayList<>();
        for (Atm atm : dataTablesOutput.getData()) {
            AtmResponseDTO atmResponseDTO = new AtmResponseDTO(atm);

            atmResponseDTOS.add(atmResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(atmResponseDTOS);

        return result;
    }

    public AtmResponseDTO findById(String id) {
        AtmResponseDTO atmResponseDTO = null;
        Optional<Atm> atmOpt = atmRepository.findById(id);
        if (atmOpt.isPresent()) {
            Atm atm = atmOpt.get();

            atmResponseDTO = new AtmResponseDTO(atm);
        }

        return atmResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Atm> atmOpt = atmRepository.findById(id);
        if (atmOpt.isPresent()) {
            atmRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Atm> atms = atmRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.atmToExcel(atms);
        return in;
    }
}
