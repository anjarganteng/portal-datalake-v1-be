package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.HapusBukuRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.HapusBukuResponseDTO;
import id.co.telkomsigma.portalapps.model.HapusBuku;
import id.co.telkomsigma.portalapps.repository.HapusBukuRepository;
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
public class HapusBukuService {

    public static final Logger LOGGER = LoggerFactory.getLogger(HapusBukuService.class);

    private HapusBukuRepository hapusBukuRepository;

    @Autowired
    public HapusBukuService(
            HapusBukuRepository hapusBukuRepository
    ) {
        this.hapusBukuRepository = hapusBukuRepository;
    }

    public void save(HapusBukuRequestDTO hapusBukuRequestDTO) {
        HapusBuku hapusBuku = null;
        Optional<HapusBuku> hapusBukuOpt = hapusBukuRepository.findById(hapusBukuRequestDTO.getSandiAntasena());

        if (hapusBukuRequestDTO.isNew()) {
            //do insert
            if (hapusBukuOpt.isPresent()) {
                throw new DuplicateDataException("Sandi Antasena you entered already exist!");
            } else {
                hapusBuku = new HapusBuku(hapusBukuRequestDTO);
            }
        } else {
            //do update
            hapusBuku = hapusBukuOpt.get();
            hapusBuku.setDeskripsiSandi(hapusBukuRequestDTO.getDeskripsiSandi());
            hapusBuku.setRupiah(hapusBukuRequestDTO.getRupiah());
            hapusBuku.setValas(hapusBukuRequestDTO.getValas());
        }

        HapusBuku saved = hapusBukuRepository.save(hapusBuku);
        LOGGER.info("Successfully save application with id: " + saved.getSandiAntasena());
    }

    public List<HapusBukuResponseDTO> getAll() {
        List<HapusBukuResponseDTO> hapusBukuResponseDTOs = null;
        List<HapusBuku> hapusBukus = hapusBukuRepository.findAll();

        if (!hapusBukus.isEmpty()) {
            hapusBukuResponseDTOs = new ArrayList<>();
            for (HapusBuku hapusBuku : hapusBukus) {
                HapusBukuResponseDTO hapusBukuResponseDTO = new HapusBukuResponseDTO(hapusBuku);
                hapusBukuResponseDTOs.add(hapusBukuResponseDTO);
            }

        }

        return hapusBukuResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<HapusBukuResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<HapusBuku> dataTablesOutput = hapusBukuRepository.findAll(dataTablesInput);

        List<HapusBukuResponseDTO> hapusBukuResponseDTOS = new ArrayList<>();
        for (HapusBuku hapusBuku : dataTablesOutput.getData()) {
            HapusBukuResponseDTO hapusBukuResponseDTO = new HapusBukuResponseDTO(hapusBuku);

            hapusBukuResponseDTOS.add(hapusBukuResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(hapusBukuResponseDTOS);

        return result;
    }

    public HapusBukuResponseDTO findById(String id) {
        HapusBukuResponseDTO hapusBukuResponseDTO = null;
        Optional<HapusBuku> hapusBukuOpt = hapusBukuRepository.findById(id);
        if (hapusBukuOpt.isPresent()) {
            HapusBuku hapusBuku = hapusBukuOpt.get();

            hapusBukuResponseDTO = new HapusBukuResponseDTO(hapusBuku);
        }

        return hapusBukuResponseDTO;
    }

    public void deleteById(String id) {
        Optional<HapusBuku> hapusBukuOpt = hapusBukuRepository.findById(id);
        if (hapusBukuOpt.isPresent()) {
            hapusBukuRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<HapusBuku> hapusBukus = hapusBukuRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.hapusBukuToExcel(hapusBukus);
        return in;
    }
}
