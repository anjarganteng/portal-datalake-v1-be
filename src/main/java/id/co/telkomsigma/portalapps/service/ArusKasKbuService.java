package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.ArusKasKbuRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ArusKasKbuResponseDTO;
import id.co.telkomsigma.portalapps.model.ArusKasKbu;
import id.co.telkomsigma.portalapps.repository.ArusKasKbuRepository;
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

@Service
public class ArusKasKbuService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ArusKasKbuService.class);

    private ArusKasKbuRepository arusKasKbuRepository;

    @Autowired
    public ArusKasKbuService(
            ArusKasKbuRepository arusKasKbuRepository
    ) {
        this.arusKasKbuRepository = arusKasKbuRepository;
    }

    public void save(ArusKasKbuRequestDTO arusKasKbuRequestDTO) {
        ArusKasKbu arusKasKbu = null;
        Optional<ArusKasKbu> arusKasKbuOpt = arusKasKbuRepository.findById(arusKasKbuRequestDTO.getUniqueId());

        if (arusKasKbuRequestDTO.isNew()) {
            //do insert
            if (arusKasKbuOpt.isPresent()) {
                throw new DuplicateDataException("Unique ID you entered already exist!");
            } else {
                arusKasKbu = new ArusKasKbu(arusKasKbuRequestDTO);
            }
        } else {
            //do update
            arusKasKbu = arusKasKbuOpt.get();
            arusKasKbu.setJenisArusKas(arusKasKbuRequestDTO.getJenisArusKas());
            arusKasKbu.setTanggalProyeksi(arusKasKbuRequestDTO.getTanggalProyeksi());
            arusKasKbu.setNominalProyeksi(arusKasKbuRequestDTO.getNominalProyeksi());
            arusKasKbu.setJenisValuta(arusKasKbuRequestDTO.getJenisValuta());
            arusKasKbu.setSandiReferensi(arusKasKbuRequestDTO.getSandiReferensi());
        }

        ArusKasKbu saved = arusKasKbuRepository.save(arusKasKbu);
        LOGGER.info("Successfully save application with id: " + saved.getUniqueId());
    }

    public List<ArusKasKbuResponseDTO> getAll() {
        List<ArusKasKbuResponseDTO> arusKasKbuResponseDTOs = null;
        List<ArusKasKbu> arusKasKbus = arusKasKbuRepository.findAll();

        if (!arusKasKbus.isEmpty()) {
            arusKasKbuResponseDTOs = new ArrayList<>();
            for (ArusKasKbu arusKasKbu : arusKasKbus) {
                ArusKasKbuResponseDTO arusKasKbuResponseDTO = new ArusKasKbuResponseDTO(arusKasKbu);
                arusKasKbuResponseDTOs.add(arusKasKbuResponseDTO);
            }

        }

        return arusKasKbuResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ArusKasKbuResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ArusKasKbu> dataTablesOutput = arusKasKbuRepository.findAll(dataTablesInput);

        List<ArusKasKbuResponseDTO> arusKasKbuResponseDTOS = new ArrayList<>();
        for (ArusKasKbu arusKasKbu : dataTablesOutput.getData()) {
            ArusKasKbuResponseDTO arusKasKbuResponseDTO = new ArusKasKbuResponseDTO(arusKasKbu);

            arusKasKbuResponseDTOS.add(arusKasKbuResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(arusKasKbuResponseDTOS);

        return result;
    }

    public ArusKasKbuResponseDTO findById(String id) {
        ArusKasKbuResponseDTO arusKasKbuResponseDTO = null;
        Optional<ArusKasKbu> arusKasKbuOpt = arusKasKbuRepository.findById(id);
        if (arusKasKbuOpt.isPresent()) {
            ArusKasKbu arusKasKbu = arusKasKbuOpt.get();

            arusKasKbuResponseDTO = new ArusKasKbuResponseDTO(arusKasKbu);
        }

        return arusKasKbuResponseDTO;
    }

    public void deleteById(String id) {
        Optional<ArusKasKbu> arusKasKbuOpt = arusKasKbuRepository.findById(id);
        if (arusKasKbuOpt.isPresent()) {
            arusKasKbuRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<ArusKasKbu> arusKasKbus = arusKasKbuRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.arusKasKbuToExcel(arusKasKbus);
        return in;
    }
}
