package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.AsetLainnyaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.AsetLainnyaResponseDTO;
import id.co.telkomsigma.portalapps.model.AsetLainnya;
import id.co.telkomsigma.portalapps.repository.AsetLainnyaRepository;
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
public class AsetLainnyaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AsetLainnyaService.class);

    private AsetLainnyaRepository asetLainnyaRepository;

    @Autowired
    public AsetLainnyaService(
            AsetLainnyaRepository asetLainnyaRepository
    ) {
        this.asetLainnyaRepository = asetLainnyaRepository;
    }

    public void save(AsetLainnyaRequestDTO asetLainnyaRequestDTO) {
        AsetLainnya asetLainnya = null;
        Optional<AsetLainnya> asetLainnyaOpt = asetLainnyaRepository.findById(asetLainnyaRequestDTO.getKodeCoa());

        if (asetLainnyaRequestDTO.isNew()) {
            // do insert
            if (asetLainnyaOpt.isPresent()) {
                throw new DuplicateDataException("Kode COA you entered already exist!");
            } else {
                asetLainnya = new AsetLainnya(asetLainnyaRequestDTO);
            }
        } else {
            // do update
            asetLainnya = asetLainnyaOpt.get();
            asetLainnya.setKeteranganJenisLainnya(asetLainnyaRequestDTO.getKeteranganJenisLainnya());
            asetLainnya.setJenisAset(asetLainnyaRequestDTO.getJenisAset());
            asetLainnya.setGolonganDebitur(asetLainnyaRequestDTO.getGolonganDebitur());
            asetLainnya.setHubunganDebitur(asetLainnyaRequestDTO.getHubunganDebitur());
            asetLainnya.setPendBknPend(asetLainnyaRequestDTO.getPendBknPend());
        }

        asetLainnyaRepository.save(asetLainnya);
        LOGGER.info("Successfully save application with id: " + asetLainnyaRequestDTO.getKodeCoa());
    }

    public List<AsetLainnyaResponseDTO> getAll() {
        List<AsetLainnyaResponseDTO> asetLainnyaResponseDTOs = null;
        List<AsetLainnya> asetLainnyas = asetLainnyaRepository.findAll();

        if (!asetLainnyas.isEmpty()) {
            asetLainnyaResponseDTOs = new ArrayList<>();
            for (AsetLainnya asetLainnya : asetLainnyas) {
                AsetLainnyaResponseDTO asetLainnyaResponseDTO = new AsetLainnyaResponseDTO(asetLainnya);
                asetLainnyaResponseDTOs.add(asetLainnyaResponseDTO);
            }

        }

        return asetLainnyaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<AsetLainnyaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<AsetLainnya> dataTablesOutput = asetLainnyaRepository.findAll(dataTablesInput);

        List<AsetLainnyaResponseDTO> asetLainnyaResponseDTOS = new ArrayList<>();
        for (AsetLainnya asetLainnya : dataTablesOutput.getData()) {
            AsetLainnyaResponseDTO asetLainnyaResponseDTO = new AsetLainnyaResponseDTO(asetLainnya);

            asetLainnyaResponseDTOS.add(asetLainnyaResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(asetLainnyaResponseDTOS);

        return result;
    }

    public AsetLainnyaResponseDTO findById(String id) {
        AsetLainnyaResponseDTO asetLainnyaResponseDTO = null;
        Optional<AsetLainnya> asetLainnyaOpt = asetLainnyaRepository.findById(id);
        if (asetLainnyaOpt.isPresent()) {
            AsetLainnya asetLainnya = asetLainnyaOpt.get();

            asetLainnyaResponseDTO = new AsetLainnyaResponseDTO(asetLainnya);
        }

        return asetLainnyaResponseDTO;
    }

    public void deleteById(String id) {
        Optional<AsetLainnya> asetLainnyaOpt = asetLainnyaRepository.findById(id);
        if (asetLainnyaOpt.isPresent()) {
            asetLainnyaRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<AsetLainnya> asetLainnyas = asetLainnyaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.asetLainnyaToExcel(asetLainnyas);
        return in;
    }
}
