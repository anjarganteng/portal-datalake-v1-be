package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PihakTerkaitRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PihakTerkaitResponseDTO;
import id.co.telkomsigma.portalapps.model.PihakTerkait;
import id.co.telkomsigma.portalapps.repository.PihakTerkaitRepository;
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
public class PihakTerkaitService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PihakTerkaitService.class);

    private PihakTerkaitRepository pihakTerkaitRepository;

    @Autowired
    public PihakTerkaitService(
            PihakTerkaitRepository pihakTerkaitRepository
    ) {
        this.pihakTerkaitRepository = pihakTerkaitRepository;
    }

    public void save(PihakTerkaitRequestDTO pihakTerkaitRequestDTO) {
        PihakTerkait pihakTerkait = null;
        Optional<PihakTerkait> pihakTerkaitOpt = pihakTerkaitRepository.findById(pihakTerkaitRequestDTO.getSandiAntasena());

        if (pihakTerkaitRequestDTO.isNew()) {
            //do insert
            if (pihakTerkaitOpt.isPresent()) {
                throw new DuplicateDataException("Sandi Antasena you entered already exist!");
            } else {
                pihakTerkait = new PihakTerkait(pihakTerkaitRequestDTO);
            }
        } else {
            //do update
            pihakTerkait = pihakTerkaitOpt.get();
            pihakTerkait.setDeskripsiSandi(pihakTerkaitRequestDTO.getDeskripsiSandi());
            pihakTerkait.setKodeGl(pihakTerkaitRequestDTO.getKodeGl());
            pihakTerkait.setDeskripsiGl(pihakTerkaitRequestDTO.getDeskripsiGl());
            pihakTerkait.setMataUang(pihakTerkaitRequestDTO.getMataUang());
        }

        PihakTerkait saved = pihakTerkaitRepository.save(pihakTerkait);
        LOGGER.info("Successfully save application with id: " + saved.getSandiAntasena());
    }

    public List<PihakTerkaitResponseDTO> getAll() {
        List<PihakTerkaitResponseDTO> pihakTerkaitResponseDTOs = null;
        List<PihakTerkait> pihakTerkaits = pihakTerkaitRepository.findAll();

        if (!pihakTerkaits.isEmpty()) {
            pihakTerkaitResponseDTOs = new ArrayList<>();
            for (PihakTerkait pihakTerkait : pihakTerkaits) {
                PihakTerkaitResponseDTO pihakTerkaitResponseDTO = new PihakTerkaitResponseDTO(pihakTerkait);
                pihakTerkaitResponseDTOs.add(pihakTerkaitResponseDTO);
            }

        }

        return pihakTerkaitResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PihakTerkaitResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<PihakTerkait> dataTablesOutput = pihakTerkaitRepository.findAll(dataTablesInput);

        List<PihakTerkaitResponseDTO> pihakTerkaitResponseDTOS = new ArrayList<>();
        for (PihakTerkait pihakTerkait : dataTablesOutput.getData()) {
            PihakTerkaitResponseDTO pihakTerkaitResponseDTO = new PihakTerkaitResponseDTO(pihakTerkait);

            pihakTerkaitResponseDTOS.add(pihakTerkaitResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(pihakTerkaitResponseDTOS);

        return result;
    }

    public PihakTerkaitResponseDTO findById(String id) {
        PihakTerkaitResponseDTO pihakTerkaitResponseDTO = null;
        Optional<PihakTerkait> pihakTerkaitOpt = pihakTerkaitRepository.findById(id);
        if (pihakTerkaitOpt.isPresent()) {
            PihakTerkait pihakTerkait = pihakTerkaitOpt.get();

            pihakTerkaitResponseDTO = new PihakTerkaitResponseDTO(pihakTerkait);
        }

        return pihakTerkaitResponseDTO;
    }

    public void deleteById(String id) {
        Optional<PihakTerkait> pihakTerkaitOpt = pihakTerkaitRepository.findById(id);
        if (pihakTerkaitOpt.isPresent()) {
            pihakTerkaitRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<PihakTerkait> pihakTerkaits = pihakTerkaitRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.pihakTerkaitToExcel(pihakTerkaits);
        return in;
    }
}
