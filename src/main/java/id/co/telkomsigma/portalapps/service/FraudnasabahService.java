package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.FraudnasabahRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.FraudnasabahResponseDTO;
import id.co.telkomsigma.portalapps.model.Fraudnasabah;
import id.co.telkomsigma.portalapps.repository.FraudnasabahRepository;
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
public class FraudnasabahService {

    public static final Logger LOGGER = LoggerFactory.getLogger(FraudnasabahService.class);

    private FraudnasabahRepository fraudnasabahRepository;

    @Autowired
    public FraudnasabahService(
            FraudnasabahRepository fraudnasabahRepository
    ) {
        this.fraudnasabahRepository = fraudnasabahRepository;
    }

    public void save(FraudnasabahRequestDTO fraudnasabahRequestDTO) {
        Fraudnasabah fraudnasabah = null;
        Optional<Fraudnasabah> fraudnasabahOpt = fraudnasabahRepository.findById(fraudnasabahRequestDTO.getNoCif());

        if (fraudnasabahOpt.isPresent()) {
            fraudnasabah = fraudnasabahRepository.findById(fraudnasabahRequestDTO.getNoCif()).get();
        } else {
            fraudnasabah = new Fraudnasabah(fraudnasabahRequestDTO);
        }

        Fraudnasabah saved = fraudnasabahRepository.save(fraudnasabah);
        LOGGER.info("Successfully save application with id: " + saved.getNoCif());
    }

    public List<FraudnasabahResponseDTO> getAll() {
        List<FraudnasabahResponseDTO> fraudnasabahResponseDTOs = null;
        List<Fraudnasabah> fraudnasabahs = fraudnasabahRepository.findAll();

        if (!fraudnasabahs.isEmpty()) {
            fraudnasabahResponseDTOs = new ArrayList<>();
            for (Fraudnasabah fraudnasabah : fraudnasabahs) {
                FraudnasabahResponseDTO fraudnasabahResponseDTO = new FraudnasabahResponseDTO(fraudnasabah);
                fraudnasabahResponseDTOs.add(fraudnasabahResponseDTO);
            }

        }

        return fraudnasabahResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<FraudnasabahResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Fraudnasabah> dataTablesOutput = fraudnasabahRepository.findAll(dataTablesInput);

        List<FraudnasabahResponseDTO> fraudnasabahResponseDTOS = new ArrayList<>();
        for (Fraudnasabah fraudnasabah : dataTablesOutput.getData()) {
            FraudnasabahResponseDTO fraudnasabahResponseDTO = new FraudnasabahResponseDTO(fraudnasabah);

            fraudnasabahResponseDTOS.add(fraudnasabahResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(fraudnasabahResponseDTOS);

        return result;
    }

    public FraudnasabahResponseDTO findById(String id) {
        FraudnasabahResponseDTO fraudnasabahResponseDTO = null;
        Optional<Fraudnasabah> fraudnasabahOpt = fraudnasabahRepository.findById(id);
        if (fraudnasabahOpt.isPresent()) {
            Fraudnasabah fraudnasabah = fraudnasabahOpt.get();

            fraudnasabahResponseDTO = new FraudnasabahResponseDTO(fraudnasabah);
        }

        return fraudnasabahResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Fraudnasabah> fraudnasabahOpt = fraudnasabahRepository.findById(id);
        if (fraudnasabahOpt.isPresent()) {
            fraudnasabahRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Fraudnasabah> fraudnasabahs = fraudnasabahRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.fraudnasabahToExcel(fraudnasabahs);
        return in;
    }
}
