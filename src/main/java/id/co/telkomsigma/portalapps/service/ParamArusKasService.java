package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.ParamArusKasRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ParamArusKasResponseDTO;
import id.co.telkomsigma.portalapps.model.ParamArusKas;
import id.co.telkomsigma.portalapps.repository.ParamArusKasRepository;
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
public class ParamArusKasService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ParamArusKasService.class);

    private ParamArusKasRepository paramArusKasRepository;

    @Autowired
    public ParamArusKasService(
            ParamArusKasRepository paramArusKasRepository
    ) {
        this.paramArusKasRepository = paramArusKasRepository;
    }

    public void save(ParamArusKasRequestDTO paramArusKasRequestDTO) {
        ParamArusKas paramArusKas = null;
        Optional<ParamArusKas> paramArusKasOpt = paramArusKasRepository.findById(paramArusKasRequestDTO.getSandiReferensi());

        if (paramArusKasRequestDTO.isNew()) {
            //do insert
            if (paramArusKasOpt.isPresent()) {
                throw new DuplicateDataException("Sandi Referensi you entered already exist!");
            } else {
                paramArusKas = new ParamArusKas(paramArusKasRequestDTO);
            }
        } else {
            //do update
            paramArusKas = paramArusKasOpt.get();
            paramArusKas.setLabel(paramArusKasRequestDTO.getLabel());
            paramArusKas.setFlagKbu(paramArusKasRequestDTO.isFlagKbu());
            paramArusKas.setFlagRpp(paramArusKasRequestDTO.isFlagRpp());
        }

        ParamArusKas saved = paramArusKasRepository.save(paramArusKas);
        LOGGER.info("Successfully save application with id: " + saved.getSandiReferensi());
    }

    public List<ParamArusKasResponseDTO> getAll() {
        List<ParamArusKasResponseDTO> paramArusKasResponseDTOs = null;
        List<ParamArusKas> paramArusKass = paramArusKasRepository.findAll();

        if (!paramArusKass.isEmpty()) {
            paramArusKasResponseDTOs = new ArrayList<>();
            for (ParamArusKas paramArusKas : paramArusKass) {
                ParamArusKasResponseDTO paramArusKasResponseDTO = new ParamArusKasResponseDTO(paramArusKas);
                paramArusKasResponseDTOs.add(paramArusKasResponseDTO);
            }

        }

        return paramArusKasResponseDTOs;
    }

    public List<ParamArusKasResponseDTO> getAllByFlagKbu(boolean flagKbu) {
        List<ParamArusKasResponseDTO> paramArusKasResponseDTOs = null;
        List<ParamArusKas> paramArusKass = paramArusKasRepository.findByFlagKbu(flagKbu);

        if (!paramArusKass.isEmpty()) {
            paramArusKasResponseDTOs = new ArrayList<>();
            for (ParamArusKas paramArusKas : paramArusKass) {
                ParamArusKasResponseDTO paramArusKasResponseDTO = new ParamArusKasResponseDTO(paramArusKas);
                paramArusKasResponseDTOs.add(paramArusKasResponseDTO);
            }

        }

        return paramArusKasResponseDTOs;
    }

    public List<ParamArusKasResponseDTO> getAllByFlagRpp(boolean flagRpp) {
        List<ParamArusKasResponseDTO> paramArusKasResponseDTOs = null;
        List<ParamArusKas> paramArusKass = paramArusKasRepository.findByFlagRpp(flagRpp);

        if (!paramArusKass.isEmpty()) {
            paramArusKasResponseDTOs = new ArrayList<>();
            for (ParamArusKas paramArusKas : paramArusKass) {
                ParamArusKasResponseDTO paramArusKasResponseDTO = new ParamArusKasResponseDTO(paramArusKas);
                paramArusKasResponseDTOs.add(paramArusKasResponseDTO);
            }

        }

        return paramArusKasResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ParamArusKasResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ParamArusKas> dataTablesOutput = paramArusKasRepository.findAll(dataTablesInput);

        List<ParamArusKasResponseDTO> paramArusKasResponseDTOS = new ArrayList<>();
        for (ParamArusKas paramArusKas : dataTablesOutput.getData()) {
            ParamArusKasResponseDTO paramArusKasResponseDTO = new ParamArusKasResponseDTO(paramArusKas);

            paramArusKasResponseDTOS.add(paramArusKasResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(paramArusKasResponseDTOS);

        return result;
    }

    public ParamArusKasResponseDTO findById(String id) {
        ParamArusKasResponseDTO paramArusKasResponseDTO = null;
        Optional<ParamArusKas> paramArusKasOpt = paramArusKasRepository.findById(id);
        if (paramArusKasOpt.isPresent()) {
            ParamArusKas paramArusKas = paramArusKasOpt.get();

            paramArusKasResponseDTO = new ParamArusKasResponseDTO(paramArusKas);
        }

        return paramArusKasResponseDTO;
    }

    public void deleteById(String id) {
        Optional<ParamArusKas> paramArusKasOpt = paramArusKasRepository.findById(id);
        if (paramArusKasOpt.isPresent()) {
            paramArusKasRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<ParamArusKas> paramArusKas = paramArusKasRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.paramArusKasToExcel(paramArusKas);
        return in;
    }
}
