package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.SetoranJaminanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.SetoranJaminanResponseDTO;
import id.co.telkomsigma.portalapps.model.SetoranJaminan;
import id.co.telkomsigma.portalapps.repository.SetoranJaminanRepository;
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
public class SetoranJaminanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SetoranJaminanService.class);

    private SetoranJaminanRepository setoranJaminanRepository;

    @Autowired
    public SetoranJaminanService(
            SetoranJaminanRepository setoranJaminanRepository
    ) {
        this.setoranJaminanRepository = setoranJaminanRepository;
    }

    public void save(SetoranJaminanRequestDTO setoranJaminanRequestDTO) {
        SetoranJaminan setoranJaminan = null;
        Optional<SetoranJaminan> setoranJaminanOpt = setoranJaminanRepository.findById(setoranJaminanRequestDTO.getKodeCoa());

        if (setoranJaminanRequestDTO.isNew()) {
            // do insert
            if (setoranJaminanOpt.isPresent()) {
                throw new DuplicateDataException("Kode COA you entered already exist!");
            } else {
                setoranJaminan = new SetoranJaminan(setoranJaminanRequestDTO);
            }
        } else {
            // do update
            setoranJaminan = setoranJaminanOpt.get();
            setoranJaminan.setDeskripsiCoa(setoranJaminanRequestDTO.getDeskripsiCoa());
            setoranJaminan.setTujuanSetoranJaminan(setoranJaminanRequestDTO.getTujuanSetoranJaminan());
            setoranJaminan.setGolonganPemilik(setoranJaminanRequestDTO.getGolonganPemilik());
            setoranJaminan.setHubDgnPelapor(setoranJaminanRequestDTO.getHubDgnPelapor());
            setoranJaminan.setPendBknPend(setoranJaminanRequestDTO.getPendBknPend());
        }

        setoranJaminanRepository.save(setoranJaminan);
        LOGGER.info("Successfully save application with id: " + setoranJaminanRequestDTO.getKodeCoa());
    }

    public List<SetoranJaminanResponseDTO> getAll() {
        List<SetoranJaminanResponseDTO> setoranJaminanResponseDTOs = null;
        List<SetoranJaminan> setoranJaminans = setoranJaminanRepository.findAll();

        if (!setoranJaminans.isEmpty()) {
            setoranJaminanResponseDTOs = new ArrayList<>();
            for (SetoranJaminan setoranJaminan : setoranJaminans) {
                SetoranJaminanResponseDTO setoranJaminanResponseDTO = new SetoranJaminanResponseDTO(setoranJaminan);
                setoranJaminanResponseDTOs.add(setoranJaminanResponseDTO);
            }

        }

        return setoranJaminanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<SetoranJaminanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<SetoranJaminan> dataTablesOutput = setoranJaminanRepository.findAll(dataTablesInput);

        List<SetoranJaminanResponseDTO> setoranJaminanResponseDTOS = new ArrayList<>();
        for (SetoranJaminan setoranJaminan : dataTablesOutput.getData()) {
            SetoranJaminanResponseDTO setoranJaminanResponseDTO = new SetoranJaminanResponseDTO(setoranJaminan);

            setoranJaminanResponseDTOS.add(setoranJaminanResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(setoranJaminanResponseDTOS);

        return result;
    }

    public SetoranJaminanResponseDTO findById(String id) {
        SetoranJaminanResponseDTO setoranJaminanResponseDTO = null;
        Optional<SetoranJaminan> setoranJaminanOpt = setoranJaminanRepository.findById(id);
        if (setoranJaminanOpt.isPresent()) {
            SetoranJaminan setoranJaminan = setoranJaminanOpt.get();

            setoranJaminanResponseDTO = new SetoranJaminanResponseDTO(setoranJaminan);
        }

        return setoranJaminanResponseDTO;
    }

    public void deleteById(String id) {
        Optional<SetoranJaminan> setoranJaminanOpt = setoranJaminanRepository.findById(id);
        if (setoranJaminanOpt.isPresent()) {
            setoranJaminanRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<SetoranJaminan> setoranJaminans = setoranJaminanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.setoranJaminanToExcel(setoranJaminans);
        return in;
    }
}
