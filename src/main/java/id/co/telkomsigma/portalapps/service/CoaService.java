package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CoaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CoaResponseDTO;
import id.co.telkomsigma.portalapps.model.Coa;
import id.co.telkomsigma.portalapps.repository.CoaRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedCoa;
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
public class CoaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CoaService.class);

    private CoaRepository coaRepository;

    @Autowired
    public CoaService(
            CoaRepository coaRepository
    ) {
        this.coaRepository = coaRepository;
    }

    public void save(CoaRequestDTO coaRequestDTO) {
        Coa coa = null;
        Optional<Coa> coaOpt = coaRepository.findByEmbedCoaCoaIndukAndEmbedCoaCoaDetail(coaRequestDTO.getCoaInduk(), coaRequestDTO.getCoaDetail());

        if (coaRequestDTO.isNew()) {
            //do insert
            if (coaOpt.isPresent()) {
                throw new DuplicateDataException("Coa Induk & Coa Detail you entered already exist!");
            } else {
                coa = new Coa(new EmbedCoa(coaRequestDTO), coaRequestDTO);
            }
        } else {
            //do update
            coa = coaOpt.get();
        }

        Coa saved = coaRepository.save(coa);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedCoa().getCoaInduk().concat(saved.getEmbedCoa().getCoaDetail()));
    }

    public List<CoaResponseDTO> getAll() {
        List<CoaResponseDTO> coaResponseDTOs = null;
        List<Coa> coas = coaRepository.findAll();

        if (!coas.isEmpty()) {
            coaResponseDTOs = new ArrayList<>();
            for (Coa coa : coas) {
                CoaResponseDTO coaResponseDTO = new CoaResponseDTO(coa);
                coaResponseDTOs.add(coaResponseDTO);
            }

        }

        return coaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CoaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Coa> dataTablesOutput = coaRepository.findAll(dataTablesInput);

        List<CoaResponseDTO> coaResponseDTOS = new ArrayList<>();
        for (Coa coa : dataTablesOutput.getData()) {
            CoaResponseDTO coaResponseDTO = new CoaResponseDTO(coa);

            coaResponseDTOS.add(coaResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(coaResponseDTOS);

        return result;
    }

    public CoaResponseDTO findById(String id, String id2) {
        CoaResponseDTO coaResponseDTO = null;
        Optional<Coa> coaOpt = coaRepository.findByEmbedCoaCoaIndukAndEmbedCoaCoaDetail(id, id2);
        if (coaOpt.isPresent()) {
            Coa coa = coaOpt.get();

            coaResponseDTO = new CoaResponseDTO(coa);
        }

        return coaResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<Coa> coaOpt = coaRepository.findByEmbedCoaCoaIndukAndEmbedCoaCoaDetail(id, id2);
        if (coaOpt.isPresent()) {
            coaRepository.deleteByEmbedCoaCoaIndukAndEmbedCoaCoaDetail(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Coa> coas = coaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.coaToExcel(coas);
        return in;
    }
}
