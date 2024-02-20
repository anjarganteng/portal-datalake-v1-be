package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.AsetAntarKantorRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.AsetAntarKantorResponseDTO;
import id.co.telkomsigma.portalapps.model.AsetAntarKantor;
import id.co.telkomsigma.portalapps.repository.AsetAntarKantorRepository;
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
public class AsetAntarKantorService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AsetAntarKantorService.class);

    private AsetAntarKantorRepository asetAntarKantorRepository;

    @Autowired
    public AsetAntarKantorService(
            AsetAntarKantorRepository asetAntarKantorRepository
    ) {
        this.asetAntarKantorRepository = asetAntarKantorRepository;
    }

    public void save(AsetAntarKantorRequestDTO asetAntarKantorRequestDTO) {
        AsetAntarKantor asetAntarKantor = null;
        Optional<AsetAntarKantor> asetAntarKantorOpt = asetAntarKantorRepository.findById(asetAntarKantorRequestDTO.getKodeCoa());

        if (asetAntarKantorRequestDTO.isNew()) {
            // do insert
            if (asetAntarKantorOpt.isPresent()) {
                throw new DuplicateDataException("Kode COA you entered already exist!");
            } else {
                asetAntarKantor = new AsetAntarKantor(asetAntarKantorRequestDTO);
            }
        } else {
            // do update
            asetAntarKantor = asetAntarKantorOpt.get();
            asetAntarKantor.setDeskripsiCoa(asetAntarKantorRequestDTO.getDeskripsiCoa());
            asetAntarKantor.setUsahaKantor(asetAntarKantorRequestDTO.getUsahaKantor());
            asetAntarKantor.setStatusKantor(asetAntarKantorRequestDTO.getStatusKantor());
            asetAntarKantor.setNegaraKantor(asetAntarKantorRequestDTO.getNegaraKantor());
            asetAntarKantor.setJenisAset(asetAntarKantorRequestDTO.getJenisAset());
            asetAntarKantor.setSukuBungaRp(asetAntarKantorRequestDTO.getSukuBungaRp());
            asetAntarKantor.setSukuBungaVl(asetAntarKantorRequestDTO.getSukuBungaVl());
        }

        asetAntarKantorRepository.save(asetAntarKantor);
        LOGGER.info("Successfully save application with id: " + asetAntarKantorRequestDTO.getKodeCoa());
    }

    public List<AsetAntarKantorResponseDTO> getAll() {
        List<AsetAntarKantorResponseDTO> asetAntarKantorResponseDTOs = null;
        List<AsetAntarKantor> asetAntarKantors = asetAntarKantorRepository.findAll();

        if (!asetAntarKantors.isEmpty()) {
            asetAntarKantorResponseDTOs = new ArrayList<>();
            for (AsetAntarKantor asetAntarKantor : asetAntarKantors) {
                AsetAntarKantorResponseDTO asetAntarKantorResponseDTO = new AsetAntarKantorResponseDTO(asetAntarKantor);
                asetAntarKantorResponseDTOs.add(asetAntarKantorResponseDTO);
            }

        }

        return asetAntarKantorResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<AsetAntarKantorResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<AsetAntarKantor> dataTablesOutput = asetAntarKantorRepository.findAll(dataTablesInput);

        List<AsetAntarKantorResponseDTO> asetAntarKantorResponseDTOS = new ArrayList<>();
        for (AsetAntarKantor asetAntarKantor : dataTablesOutput.getData()) {
            AsetAntarKantorResponseDTO asetAntarKantorResponseDTO = new AsetAntarKantorResponseDTO(asetAntarKantor);

            asetAntarKantorResponseDTOS.add(asetAntarKantorResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(asetAntarKantorResponseDTOS);

        return result;
    }

    public AsetAntarKantorResponseDTO findById(String id) {
        AsetAntarKantorResponseDTO asetAntarKantorResponseDTO = null;
        Optional<AsetAntarKantor> asetAntarKantorOpt = asetAntarKantorRepository.findById(id);
        if (asetAntarKantorOpt.isPresent()) {
            AsetAntarKantor asetAntarKantor = asetAntarKantorOpt.get();

            asetAntarKantorResponseDTO = new AsetAntarKantorResponseDTO(asetAntarKantor);
        }

        return asetAntarKantorResponseDTO;
    }

    public void deleteById(String id) {
        Optional<AsetAntarKantor> asetAntarKantorOpt = asetAntarKantorRepository.findById(id);
        if (asetAntarKantorOpt.isPresent()) {
            asetAntarKantorRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<AsetAntarKantor> asetAntarKantors = asetAntarKantorRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.asetAntarKantorToExcel(asetAntarKantors);
        return in;
    }
}
