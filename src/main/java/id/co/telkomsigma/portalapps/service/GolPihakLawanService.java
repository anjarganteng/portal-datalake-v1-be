package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.GolPihakLawanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.GolPihakLawanResponseDTO;
import id.co.telkomsigma.portalapps.model.GolPihakLawan;
import id.co.telkomsigma.portalapps.repository.GolPihakLawanRepository;
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
public class GolPihakLawanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GolPihakLawanService.class);

    private GolPihakLawanRepository golPihakLawanRepository;

    @Autowired
    public GolPihakLawanService(
            GolPihakLawanRepository golPihakLawanRepository
    ) {
        this.golPihakLawanRepository = golPihakLawanRepository;
    }

    public void save(GolPihakLawanRequestDTO golPihakLawanRequestDTO) {
        GolPihakLawan golPihakLawan = null;
        Optional<GolPihakLawan> golPihakLawanOpt = golPihakLawanRepository.findById(golPihakLawanRequestDTO.getGolonganPihakLawan());

        if (golPihakLawanRequestDTO.isNew()) {
            //do insert
            if (golPihakLawanOpt.isPresent()) {
                throw new DuplicateDataException("Golongan Pihak Lawan you entered already exist!");
            } else {
                golPihakLawan = new GolPihakLawan(golPihakLawanRequestDTO);
            }
        } else {
            //do update
            golPihakLawan = golPihakLawanOpt.get();
            golPihakLawan.setGolDebiturKreditur(golPihakLawanRequestDTO.getGolDebiturKreditur());
        }

        GolPihakLawan saved = golPihakLawanRepository.save(golPihakLawan);
        LOGGER.info("Successfully save application with id: " + saved.getGolonganPihakLawan());
    }

    public List<GolPihakLawanResponseDTO> getAll() {
        List<GolPihakLawanResponseDTO> golPihakLawanResponseDTOs = null;
        List<GolPihakLawan> golPihakLawans = golPihakLawanRepository.findAll();

        if (!golPihakLawans.isEmpty()) {
            golPihakLawanResponseDTOs = new ArrayList<>();
            for (GolPihakLawan golPihakLawan : golPihakLawans) {
                GolPihakLawanResponseDTO golPihakLawanResponseDTO = new GolPihakLawanResponseDTO(golPihakLawan);
                golPihakLawanResponseDTOs.add(golPihakLawanResponseDTO);
            }

        }

        return golPihakLawanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<GolPihakLawanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<GolPihakLawan> dataTablesOutput = golPihakLawanRepository.findAll(dataTablesInput);

        List<GolPihakLawanResponseDTO> golPihakLawanResponseDTOS = new ArrayList<>();
        for (GolPihakLawan golPihakLawan : dataTablesOutput.getData()) {
            GolPihakLawanResponseDTO golPihakLawanResponseDTO = new GolPihakLawanResponseDTO(golPihakLawan);

            golPihakLawanResponseDTOS.add(golPihakLawanResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(golPihakLawanResponseDTOS);

        return result;
    }

    public GolPihakLawanResponseDTO findById(String id) {
        GolPihakLawanResponseDTO golPihakLawanResponseDTO = null;
        Optional<GolPihakLawan> golPihakLawanOpt = golPihakLawanRepository.findById(id);
        if (golPihakLawanOpt.isPresent()) {
            GolPihakLawan golPihakLawan = golPihakLawanOpt.get();

            golPihakLawanResponseDTO = new GolPihakLawanResponseDTO(golPihakLawan);
        }

        return golPihakLawanResponseDTO;
    }

    public void deleteById(String id) {
        Optional<GolPihakLawan> golPihakLawanOpt = golPihakLawanRepository.findById(id);
        if (golPihakLawanOpt.isPresent()) {
            golPihakLawanRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<GolPihakLawan> golPihakLawans = golPihakLawanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.golPihakLawanToExcel(golPihakLawans);
        return in;
    }
}
