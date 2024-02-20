package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.ReferensiAntasenaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ReferensiAntasenaResponseDTO;
import id.co.telkomsigma.portalapps.model.ReferensiAntasena;
import id.co.telkomsigma.portalapps.repository.ReferensiAntasenaRepository;
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
public class ReferensiAntasenaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReferensiAntasenaService.class);

    private ReferensiAntasenaRepository referensiAntasenaRepository;

    @Autowired
    public ReferensiAntasenaService(
            ReferensiAntasenaRepository referensiAntasenaRepository
    ) {
        this.referensiAntasenaRepository = referensiAntasenaRepository;
    }

    public void save(ReferensiAntasenaRequestDTO referensiAntasenaRequestDTO) {
        ReferensiAntasena referensiAntasena = null;
        if (referensiAntasenaRequestDTO.getNo() != null) {
            // do update
            referensiAntasena = referensiAntasenaRepository.findById(referensiAntasenaRequestDTO.getNo()).get();
            referensiAntasena.setSandiExisting(referensiAntasenaRequestDTO.getSandiExisting());
        } else {
            // do insert
            referensiAntasena = new ReferensiAntasena(referensiAntasenaRequestDTO);
        }

        ReferensiAntasena saved = referensiAntasenaRepository.save(referensiAntasena);
        LOGGER.info("Successfully save application with id: " + saved.getNo());
    }

    public List<ReferensiAntasenaResponseDTO> getAll() {
        List<ReferensiAntasenaResponseDTO> referensiAntasenaResponseDTOs = null;
        List<ReferensiAntasena> referensiAntasenas = referensiAntasenaRepository.findAll();

        if (!referensiAntasenas.isEmpty()) {
            referensiAntasenaResponseDTOs = new ArrayList<>();
            for (ReferensiAntasena referensiAntasena : referensiAntasenas) {
                ReferensiAntasenaResponseDTO referensiAntasenaResponseDTO = new ReferensiAntasenaResponseDTO(referensiAntasena);
                referensiAntasenaResponseDTOs.add(referensiAntasenaResponseDTO);
            }

        }

        return referensiAntasenaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ReferensiAntasenaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ReferensiAntasena> dataTablesOutput = referensiAntasenaRepository.findAll(dataTablesInput);

        List<ReferensiAntasenaResponseDTO> referensiAntasenaResponseDTOS = new ArrayList<>();
        for (ReferensiAntasena referensiAntasena : dataTablesOutput.getData()) {
            ReferensiAntasenaResponseDTO referensiAntasenaResponseDTO = new ReferensiAntasenaResponseDTO(referensiAntasena);

            referensiAntasenaResponseDTOS.add(referensiAntasenaResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(referensiAntasenaResponseDTOS);

        return result;
    }

    public ReferensiAntasenaResponseDTO findById(Long id) {
        ReferensiAntasenaResponseDTO referensiAntasenaResponseDTO = null;
        Optional<ReferensiAntasena> referensiAntasenaOpt = referensiAntasenaRepository.findById(id);
        if (referensiAntasenaOpt.isPresent()) {
            ReferensiAntasena referensiAntasena = referensiAntasenaOpt.get();

            referensiAntasenaResponseDTO = new ReferensiAntasenaResponseDTO(referensiAntasena);
        }

        return referensiAntasenaResponseDTO;
    }

    public void deleteById(Long id) {
        Optional<ReferensiAntasena> applicationOpt = referensiAntasenaRepository.findById(id);
        if (applicationOpt.isPresent()) {
            referensiAntasenaRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<ReferensiAntasena> referensiAntasenas = referensiAntasenaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.referensiAntasenaToExcel(referensiAntasenas);
        return in;
    }
}
