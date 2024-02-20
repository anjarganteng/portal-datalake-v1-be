package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.LimitSimpanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.LimitSimpananResponseDTO;
import id.co.telkomsigma.portalapps.model.LimitSimpanan;
import id.co.telkomsigma.portalapps.repository.LimitSimpananRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class LimitSimpananService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LimitSimpananService.class);

    private LimitSimpananRepository limitSimpananRepository;

    @Autowired
    public LimitSimpananService(
            LimitSimpananRepository limitSimpananRepository
    ) {
        this.limitSimpananRepository = limitSimpananRepository;
    }

    public void save(LimitSimpanRequestDTO limitSimpanRequestDTO) {
        LimitSimpanan limitSimpanan = null;
        Optional<LimitSimpanan> limitSimpananOpt = limitSimpananRepository.findById(limitSimpanRequestDTO.getId());

        if (limitSimpanRequestDTO.isNew()) {
            // do insert
            if (limitSimpananOpt.isPresent()) {
                throw new DuplicateDataException("id you entered already exist!");
            } else {
                limitSimpanan = new LimitSimpanan(limitSimpanRequestDTO);
            }
        } else {
            // do update
            limitSimpanan = limitSimpananOpt.get();
            limitSimpanan.setNominalDijamin(limitSimpanRequestDTO.getNominalDijamin());
        }

        limitSimpananRepository.save(limitSimpanan);
        LOGGER.info("Successfully save application with id: " + limitSimpanRequestDTO.getId());
    }

    public List<LimitSimpananResponseDTO> getAll() {
        List<LimitSimpananResponseDTO> limitSimpananResponseDTOs = null;
        List<LimitSimpanan> limitSimpanans = limitSimpananRepository.findAll();

        if (!limitSimpanans.isEmpty()) {
            limitSimpananResponseDTOs = new ArrayList<>();
            for (LimitSimpanan limitSimpanan : limitSimpanans) {
                LimitSimpananResponseDTO limitSimpananResponseDTO = new LimitSimpananResponseDTO(limitSimpanan);
                limitSimpananResponseDTOs.add(limitSimpananResponseDTO);
            }

        }

        return limitSimpananResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<LimitSimpananResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<LimitSimpanan> dataTablesOutput = limitSimpananRepository.findAll(dataTablesInput);

        List<LimitSimpananResponseDTO> limitSimpananResponseDTOS = new ArrayList<>();
        for (LimitSimpanan limitSimpanan : dataTablesOutput.getData()) {
            LimitSimpananResponseDTO limitSimpananResponseDTO = new LimitSimpananResponseDTO(limitSimpanan);

            limitSimpananResponseDTOS.add(limitSimpananResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(limitSimpananResponseDTOS);

        return result;
    }

    public LimitSimpananResponseDTO findById(String id) {
        LimitSimpananResponseDTO limitSimpananResponseDTO = null;
        Optional<LimitSimpanan> limitSimpananOpt = limitSimpananRepository.findById(id);
        if (limitSimpananOpt.isPresent()) {
            LimitSimpanan limitSimpanan = limitSimpananOpt.get();

            limitSimpananResponseDTO = new LimitSimpananResponseDTO(limitSimpanan);
        }

        return limitSimpananResponseDTO;
    }

    public void deleteById(String id) {
        Optional<LimitSimpanan> limitSimpananOpt = limitSimpananRepository.findById(id);
        if (limitSimpananOpt.isPresent()) {
            limitSimpananRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }
}
