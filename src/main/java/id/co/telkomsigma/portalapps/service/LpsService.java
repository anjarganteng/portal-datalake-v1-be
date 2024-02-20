package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.LpsRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.LpsResponseDTO;
import id.co.telkomsigma.portalapps.model.Lps;
import id.co.telkomsigma.portalapps.repository.LpsRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedLps;
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
public class LpsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LpsService.class);

    private LpsRepository lpsRepository;

    @Autowired
    public LpsService(
            LpsRepository lpsRepository
    ) {
        this.lpsRepository = lpsRepository;
    }

    public void save(LpsRequestDTO lpsRequestDTO) {
        Lps lps = null;
        Optional<Lps> lpsOpt = lpsRepository.findByEmbedLpsTglMulaiAndEmbedLpsJenisValuta(lpsRequestDTO.getTglMulai(), lpsRequestDTO.getJenisValuta());

        if (lpsRequestDTO.isNew()) {
            //do insert
            if (lpsOpt.isPresent()) {
                throw new DuplicateDataException("Tanggal Mulai & Jenis Valuta you entered already exist!");
            } else {
                lps = new Lps(new EmbedLps(lpsRequestDTO), lpsRequestDTO);
            }
        } else {
            //do update
            lps = lpsOpt.get();
            lps.setTglJt(lpsRequestDTO.getTglJt());
            lps.setSukuBunga(lpsRequestDTO.getSukuBunga());
        }

        Lps saved = lpsRepository.save(lps);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedLps().getTglMulai());
    }

    public List<LpsResponseDTO> getAll() {
        List<LpsResponseDTO> lpsResponseDTOs = null;
        List<Lps> lpss = lpsRepository.findAll();

        if (!lpss.isEmpty()) {
            lpsResponseDTOs = new ArrayList<>();
            for (Lps lps : lpss) {
                LpsResponseDTO lpsResponseDTO = new LpsResponseDTO(lps);
                lpsResponseDTOs.add(lpsResponseDTO);
            }

        }

        return lpsResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<LpsResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Lps> dataTablesOutput = lpsRepository.findAll(dataTablesInput);

        List<LpsResponseDTO> lpsResponseDTOS = new ArrayList<>();
        for (Lps lps : dataTablesOutput.getData()) {
            LpsResponseDTO lpsResponseDTO = new LpsResponseDTO(lps);

            lpsResponseDTOS.add(lpsResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(lpsResponseDTOS);

        return result;
    }

    public LpsResponseDTO findById(String id, String id2) {
        LpsResponseDTO lpsResponseDTO = null;
        Optional<Lps> lpsOpt = lpsRepository.findByEmbedLpsTglMulaiAndEmbedLpsJenisValuta(id, id2);
        if (lpsOpt.isPresent()) {
            Lps lps = lpsOpt.get();

            lpsResponseDTO = new LpsResponseDTO(lps);
        }

        return lpsResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<Lps> lpsOpt = lpsRepository.findByEmbedLpsTglMulaiAndEmbedLpsJenisValuta(id, id2);
        if (lpsOpt.isPresent()) {
            lpsRepository.deleteByEmbedLpsTglMulaiAndEmbedLpsJenisValuta(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Lps> lpss = lpsRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.bungaLpsToExcel(lpss);
        return in;
    }
}

