package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PtsRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PtsResponseDTO;
import id.co.telkomsigma.portalapps.model.Pts;
import id.co.telkomsigma.portalapps.repository.PtsRepository;
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
public class PtsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PtsService.class);

    private PtsRepository ptsRepository;

    @Autowired
    public PtsService(
            PtsRepository ptsRepository
    ) {
        this.ptsRepository = ptsRepository;
    }

    public void save(PtsRequestDTO ptsRequestDTO) {
        Pts pts = null;
        Optional<Pts> ptsOpt = ptsRepository.findById(ptsRequestDTO.getSandiAntasena());

        if (ptsRequestDTO.isNew()) {
            //do insert
            if (ptsOpt.isPresent()) {
                throw new DuplicateDataException("Sandi Antasena you entered already exist!");
            } else {
                pts = new Pts(ptsRequestDTO);
            }
        } else {
            //do update
            pts = ptsOpt.get();
            pts.setDeskripsiGl(ptsRequestDTO.getDeskripsiGl());
            pts.setDeskripsiSandi(ptsRequestDTO.getDeskripsiSandi());
            pts.setMataUang(ptsRequestDTO.getMataUang());
            pts.setKodeGl(ptsRequestDTO.getKodeGl());
        }

        Pts saved = ptsRepository.save(pts);
        LOGGER.info("Successfully save application with id: " + saved.getSandiAntasena());
    }

    public List<PtsResponseDTO> getAll() {
        List<PtsResponseDTO> ptsResponseDTOs = null;
        List<Pts> ptss = ptsRepository.findAll();

        if (!ptss.isEmpty()) {
            ptsResponseDTOs = new ArrayList<>();
            for (Pts pts : ptss) {
                PtsResponseDTO ptsResponseDTO = new PtsResponseDTO(pts);
                ptsResponseDTOs.add(ptsResponseDTO);
            }

        }

        return ptsResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PtsResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Pts> dataTablesOutput = ptsRepository.findAll(dataTablesInput);

        List<PtsResponseDTO> ptsResponseDTOS = new ArrayList<>();
        for (Pts pts : dataTablesOutput.getData()) {
            PtsResponseDTO ptsResponseDTO = new PtsResponseDTO(pts);

            ptsResponseDTOS.add(ptsResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(ptsResponseDTOS);

        return result;
    }

    public PtsResponseDTO findById(String id) {
        PtsResponseDTO ptsResponseDTO = null;
        Optional<Pts> ptsOpt = ptsRepository.findById(id);
        if (ptsOpt.isPresent()) {
            Pts pts = ptsOpt.get();

            ptsResponseDTO = new PtsResponseDTO(pts);
        }

        return ptsResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Pts> ptsOpt = ptsRepository.findById(id);
        if (ptsOpt.isPresent()) {
            ptsRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Pts> ptss = ptsRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.ptsToExcel(ptss);
        return in;
    }
}
