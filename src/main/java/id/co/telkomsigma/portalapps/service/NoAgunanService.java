package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.NoAgunanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.NoAgunanResponseDTO;
import id.co.telkomsigma.portalapps.model.NoAgunan;
import id.co.telkomsigma.portalapps.repository.NoAgunanRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedNoAgunan;
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
public class NoAgunanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(NoAgunanService.class);

    private NoAgunanRepository noAgunanRepository;

    @Autowired
    public NoAgunanService(
            NoAgunanRepository noAgunanRepository
    ) {
        this.noAgunanRepository = noAgunanRepository;
    }

    public void save(NoAgunanRequestDTO noAgunanRequestDTO) {
        NoAgunan noAgunan = null;
        Optional<NoAgunan> noAgunanOpt = noAgunanRepository.findByEmbedNoAgunanNoAgunanBaruAndEmbedNoAgunanNoAgunanLama(noAgunanRequestDTO.getNoAgunanBaru(), noAgunanRequestDTO.getNoAgunanLama());

        if (noAgunanRequestDTO.isNew()) {
            //do insert
            if (noAgunanOpt.isPresent()) {
                throw new DuplicateDataException("No Agunan Baru & No Agunan Lama you entered already exist!");
            } else {
                noAgunan = new NoAgunan(new EmbedNoAgunan(noAgunanRequestDTO), noAgunanRequestDTO);
            }
        } else {
            //do update
            noAgunan = noAgunanOpt.get();
        }

        NoAgunan saved = noAgunanRepository.save(noAgunan);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedNoAgunan().getNoAgunanBaru().concat(saved.getEmbedNoAgunan().getNoAgunanLama()));
    }

    public List<NoAgunanResponseDTO> getAll() {
        List<NoAgunanResponseDTO> noAgunanResponseDTOs = null;
        List<NoAgunan> noAgunans = noAgunanRepository.findAll();

        if (!noAgunans.isEmpty()) {
            noAgunanResponseDTOs = new ArrayList<>();
            for (NoAgunan noAgunan : noAgunans) {
                NoAgunanResponseDTO noAgunanResponseDTO = new NoAgunanResponseDTO(noAgunan);
                noAgunanResponseDTOs.add(noAgunanResponseDTO);
            }

        }

        return noAgunanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<NoAgunanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<NoAgunan> dataTablesOutput = noAgunanRepository.findAll(dataTablesInput);

        List<NoAgunanResponseDTO> noAgunanResponseDTOS = new ArrayList<>();
        for (NoAgunan noAgunan : dataTablesOutput.getData()) {
            NoAgunanResponseDTO noAgunanResponseDTO = new NoAgunanResponseDTO(noAgunan);

            noAgunanResponseDTOS.add(noAgunanResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(noAgunanResponseDTOS);

        return result;
    }

    public NoAgunanResponseDTO findById(String id, String id2) {
        NoAgunanResponseDTO noAgunanResponseDTO = null;
        Optional<NoAgunan> noAgunanOpt = noAgunanRepository.findByEmbedNoAgunanNoAgunanBaruAndEmbedNoAgunanNoAgunanLama(id, id2);
        if (noAgunanOpt.isPresent()) {
            NoAgunan noAgunan = noAgunanOpt.get();

            noAgunanResponseDTO = new NoAgunanResponseDTO(noAgunan);
        }

        return noAgunanResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<NoAgunan> noAgunanOpt = noAgunanRepository.findByEmbedNoAgunanNoAgunanBaruAndEmbedNoAgunanNoAgunanLama(id, id2);
        if (noAgunanOpt.isPresent()) {
            noAgunanRepository.deleteByEmbedNoAgunanNoAgunanBaruAndEmbedNoAgunanNoAgunanLama(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<NoAgunan> noAgunans = noAgunanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.noAgunanToExcel(noAgunans);
        return in;
    }
}
