package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.PengecualianKodeAgunanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PengecualianKodeAgunanResponseDTO;
import id.co.telkomsigma.portalapps.model.PengecualianKodeAgunan;
import id.co.telkomsigma.portalapps.repository.PengecualianKodeAgunanRepository;
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
public class PengecualianKodeAgunanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PengecualianKodeAgunanService.class);

    private PengecualianKodeAgunanRepository pengecualianKodeAgunanRepository;

    @Autowired
    public PengecualianKodeAgunanService(
            PengecualianKodeAgunanRepository pengecualianKodeAgunanRepository
    ) {
        this.pengecualianKodeAgunanRepository = pengecualianKodeAgunanRepository;
    }

    public void save(PengecualianKodeAgunanRequestDTO pengecualianKodeAgunanRequestDTO) {
        PengecualianKodeAgunan pengecualianKodeAgunan = null;
        Optional<PengecualianKodeAgunan> pengecualianKodeAgunanOpt = pengecualianKodeAgunanRepository.findById(pengecualianKodeAgunanRequestDTO.getKodeAgunanCore());

        if (pengecualianKodeAgunanOpt.isPresent()) {
            pengecualianKodeAgunan = pengecualianKodeAgunanRepository.findById(pengecualianKodeAgunanRequestDTO.getKodeAgunanCore()).get();
            pengecualianKodeAgunan.setKodeAgunanCore(pengecualianKodeAgunanRequestDTO.getKodeAgunanCore());
        } else {
            pengecualianKodeAgunan = new PengecualianKodeAgunan(pengecualianKodeAgunanRequestDTO);
        }

        PengecualianKodeAgunan saved = pengecualianKodeAgunanRepository.save(pengecualianKodeAgunan);
        LOGGER.info("Successfully save application with id: " + saved.getKodeAgunanCore());
    }

    public List<PengecualianKodeAgunanResponseDTO> getAll() {
        List<PengecualianKodeAgunanResponseDTO> pengecualianKodeAgunanResponseDTOs = null;
        List<PengecualianKodeAgunan> pengecualianKodeAgunans = pengecualianKodeAgunanRepository.findAll();

        if (!pengecualianKodeAgunans.isEmpty()) {
            pengecualianKodeAgunanResponseDTOs = new ArrayList<>();
            for (PengecualianKodeAgunan pengecualianKodeAgunan : pengecualianKodeAgunans) {
                PengecualianKodeAgunanResponseDTO pengecualianKodeAgunanResponseDTO = new PengecualianKodeAgunanResponseDTO(pengecualianKodeAgunan);
                pengecualianKodeAgunanResponseDTOs.add(pengecualianKodeAgunanResponseDTO);
            }

        }

        return pengecualianKodeAgunanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PengecualianKodeAgunanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<PengecualianKodeAgunan> dataTablesOutput = pengecualianKodeAgunanRepository.findAll(dataTablesInput);

        List<PengecualianKodeAgunanResponseDTO> pengecualianKodeAgunanResponseDTOS = new ArrayList<>();
        for (PengecualianKodeAgunan pengecualianKodeAgunan : dataTablesOutput.getData()) {
            PengecualianKodeAgunanResponseDTO pengecualianKodeAgunanResponseDTO = new PengecualianKodeAgunanResponseDTO(pengecualianKodeAgunan);

            pengecualianKodeAgunanResponseDTOS.add(pengecualianKodeAgunanResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(pengecualianKodeAgunanResponseDTOS);

        return result;
    }

    public PengecualianKodeAgunanResponseDTO findById(String id) {
        PengecualianKodeAgunanResponseDTO pengecualianKodeAgunanResponseDTO = null;
        Optional<PengecualianKodeAgunan> pengecualianKodeAgunanOpt = pengecualianKodeAgunanRepository.findById(id);
        if (pengecualianKodeAgunanOpt.isPresent()) {
            PengecualianKodeAgunan pengecualianKodeAgunan = pengecualianKodeAgunanOpt.get();

            pengecualianKodeAgunanResponseDTO = new PengecualianKodeAgunanResponseDTO(pengecualianKodeAgunan);
        }

        return pengecualianKodeAgunanResponseDTO;
    }

    public void deleteById(String id) {
        Optional<PengecualianKodeAgunan> pengecualianKodeAgunanOpt = pengecualianKodeAgunanRepository.findById(id);
        if (pengecualianKodeAgunanOpt.isPresent()) {
            pengecualianKodeAgunanRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<PengecualianKodeAgunan> pengecualianKodeAgunans = pengecualianKodeAgunanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.pengecualianKodeAgunanToExcel(pengecualianKodeAgunans);
        return in;
    }
}
