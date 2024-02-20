package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.ArusKasRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ArusKasResponseDTO;
import id.co.telkomsigma.portalapps.model.ArusKas;
import id.co.telkomsigma.portalapps.repository.ArusKasRepository;
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
public class ArusKasService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ArusKasService.class);

    private ArusKasRepository arusKasRepository;

    @Autowired
    public ArusKasService(
            ArusKasRepository arusKasRepository
    ) {
        this.arusKasRepository = arusKasRepository;
    }

    public void save(ArusKasRequestDTO arusKasRequestDTO) {
        ArusKas arusKas = null;
        Optional<ArusKas> arusKasOpt = arusKasRepository.findById(arusKasRequestDTO.getUniqueId());

        if (arusKasRequestDTO.isNew()) {
            //do insert
            if (arusKasOpt.isPresent()) {
                throw new DuplicateDataException("Unique ID you entered already exist!");
            } else {
                arusKas = new ArusKas(arusKasRequestDTO);
            }
        } else {
            //do update
            arusKas = arusKasOpt.get();
            arusKas.setJenisArusKas(arusKasRequestDTO.getJenisArusKas());
            arusKas.setTanggalProyeksi(arusKasRequestDTO.getTanggalProyeksi());
            arusKas.setNominalProyeksi(arusKasRequestDTO.getNominalProyeksi());
            arusKas.setJenisValuta(arusKasRequestDTO.getJenisValuta());
            arusKas.setSandiReferensi(arusKasRequestDTO.getSandiReferensi());
        }

        ArusKas saved = arusKasRepository.save(arusKas);
        LOGGER.info("Successfully save application with id: " + saved.getUniqueId());
    }

    public List<ArusKasResponseDTO> getAll() {
        List<ArusKasResponseDTO> arusKasResponseDTOs = null;
        List<ArusKas> arusKass = arusKasRepository.findAll();

        if (!arusKass.isEmpty()) {
            arusKasResponseDTOs = new ArrayList<>();
            for (ArusKas ArusKas : arusKass) {
                ArusKasResponseDTO ArusKasResponseDTO = new ArusKasResponseDTO(ArusKas);
                arusKasResponseDTOs.add(ArusKasResponseDTO);
            }

        }

        return arusKasResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ArusKasResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ArusKas> dataTablesOutput = arusKasRepository.findAll(dataTablesInput);

        List<ArusKasResponseDTO> arusKasResponseDTOS = new ArrayList<>();
        for (ArusKas ArusKas : dataTablesOutput.getData()) {
            ArusKasResponseDTO ArusKasResponseDTO = new ArusKasResponseDTO(ArusKas);

            arusKasResponseDTOS.add(ArusKasResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(arusKasResponseDTOS);

        return result;
    }

    public ArusKasResponseDTO findById(String id) {
        ArusKasResponseDTO ArusKasResponseDTO = null;
        Optional<ArusKas> ArusKasOpt = arusKasRepository.findById(id);
        if (ArusKasOpt.isPresent()) {
            ArusKas ArusKas = ArusKasOpt.get();

            ArusKasResponseDTO = new ArusKasResponseDTO(ArusKas);
        }

        return ArusKasResponseDTO;
    }

    public void deleteById(String id) {
        Optional<ArusKas> ArusKasOpt = arusKasRepository.findById(id);
        if (ArusKasOpt.isPresent()) {
            arusKasRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<ArusKas> arusKas = arusKasRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.arusKasToExcel(arusKas);
        return in;
    }
}
