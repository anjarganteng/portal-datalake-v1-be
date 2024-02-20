package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.GolonganPemilikIndividuRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.GolonganPemilikIndividuResponseDTO;
import id.co.telkomsigma.portalapps.model.GolonganPemilikIndividu;
import id.co.telkomsigma.portalapps.repository.GolonganPemilikIndividuRepository;
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
public class GolonganPemilikIndividuService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GolonganPemilikIndividuService.class);

    private GolonganPemilikIndividuRepository golonganPemilikIndividuRepository;

    @Autowired
    public GolonganPemilikIndividuService(
            GolonganPemilikIndividuRepository golonganPemilikIndividuRepository
    ) {
        this.golonganPemilikIndividuRepository = golonganPemilikIndividuRepository;
    }

    public void save(GolonganPemilikIndividuRequestDTO golonganPemilikIndividuRequestDTO) {
        GolonganPemilikIndividu golonganPemilikIndividu = null;
        Optional<GolonganPemilikIndividu> golonganPemilikIndividuOpt = golonganPemilikIndividuRepository.findById(golonganPemilikIndividuRequestDTO.getTipeGolPemilik());

        if (golonganPemilikIndividuOpt.isPresent()) {
            golonganPemilikIndividu = golonganPemilikIndividuRepository.findById(golonganPemilikIndividuRequestDTO.getTipeGolPemilik()).get();
        } else {
            golonganPemilikIndividu = new GolonganPemilikIndividu(golonganPemilikIndividuRequestDTO);
        }

        GolonganPemilikIndividu saved = golonganPemilikIndividuRepository.save(golonganPemilikIndividu);
        LOGGER.info("Successfully save application with id: " + saved.getTipeGolPemilik());
    }

    public List<GolonganPemilikIndividuResponseDTO> getAll() {
        List<GolonganPemilikIndividuResponseDTO> golonganPemilikIndividuResponseDTOs = null;
        List<GolonganPemilikIndividu> golonganPemilikIndividus = golonganPemilikIndividuRepository.findAll();

        if (!golonganPemilikIndividus.isEmpty()) {
            golonganPemilikIndividuResponseDTOs = new ArrayList<>();
            for (GolonganPemilikIndividu golonganPemilikIndividu : golonganPemilikIndividus) {
                GolonganPemilikIndividuResponseDTO golonganPemilikIndividuResponseDTO = new GolonganPemilikIndividuResponseDTO(golonganPemilikIndividu);
                golonganPemilikIndividuResponseDTOs.add(golonganPemilikIndividuResponseDTO);
            }

        }

        return golonganPemilikIndividuResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<GolonganPemilikIndividuResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<GolonganPemilikIndividu> dataTablesOutput = golonganPemilikIndividuRepository.findAll(dataTablesInput);

        List<GolonganPemilikIndividuResponseDTO> golonganPemilikIndividuResponseDTOS = new ArrayList<>();
        for (GolonganPemilikIndividu golonganPemilikIndividu : dataTablesOutput.getData()) {
            GolonganPemilikIndividuResponseDTO golonganPemilikIndividuResponseDTO = new GolonganPemilikIndividuResponseDTO(golonganPemilikIndividu);

            golonganPemilikIndividuResponseDTOS.add(golonganPemilikIndividuResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(golonganPemilikIndividuResponseDTOS);

        return result;
    }

    public GolonganPemilikIndividuResponseDTO findById(String id) {
        GolonganPemilikIndividuResponseDTO golonganPemilikIndividuResponseDTO = null;
        Optional<GolonganPemilikIndividu> golonganPemilikIndividuOpt = golonganPemilikIndividuRepository.findById(id);
        if (golonganPemilikIndividuOpt.isPresent()) {
            GolonganPemilikIndividu golonganPemilikIndividu = golonganPemilikIndividuOpt.get();

            golonganPemilikIndividuResponseDTO = new GolonganPemilikIndividuResponseDTO(golonganPemilikIndividu);
        }

        return golonganPemilikIndividuResponseDTO;
    }

    public void deleteById(String id) {
        Optional<GolonganPemilikIndividu> golonganPemilikIndividuOpt = golonganPemilikIndividuRepository.findById(id);
        if (golonganPemilikIndividuOpt.isPresent()) {
            golonganPemilikIndividuRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<GolonganPemilikIndividu> golonganPemilikIndividus = golonganPemilikIndividuRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.golPemilikIndToExcel(golonganPemilikIndividus);
        return in;
    }
}
