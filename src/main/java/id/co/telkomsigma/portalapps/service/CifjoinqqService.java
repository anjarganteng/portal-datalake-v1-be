package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CifjoinqqRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CifjoinqqResponseDTO;
import id.co.telkomsigma.portalapps.model.Cifjoinqq;
import id.co.telkomsigma.portalapps.repository.CifjoinqqRepository;
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
public class CifjoinqqService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CifjoinqqService.class);

    private CifjoinqqRepository cifjoinqqRepository;

    @Autowired
    public CifjoinqqService(
            CifjoinqqRepository cifjoinqqRepository
    ) {
        this.cifjoinqqRepository = cifjoinqqRepository;
    }

    public void save(CifjoinqqRequestDTO cifjoinqqRequestDTO) {
        Cifjoinqq cifjoinqq = null;
        Optional<Cifjoinqq> cifjoinqqOpt = cifjoinqqRepository.findById(cifjoinqqRequestDTO.getCifQq());

        if (cifjoinqqRequestDTO.isNew()) {
            // do insert
            if (cifjoinqqOpt.isPresent()) {
                throw new DuplicateDataException("Cif QQ you entered already exist!");
            } else {
                cifjoinqq = new Cifjoinqq(cifjoinqqRequestDTO);
            }
        } else {
            // do update
            cifjoinqq = cifjoinqqOpt.get();
            cifjoinqq.setNamaLengkap(cifjoinqqRequestDTO.getNamaLengkap());
        }

        cifjoinqqRepository.save(cifjoinqq);
        LOGGER.info("Successfully save application with id: " + cifjoinqqRequestDTO.getCifQq());
    }

    public List<CifjoinqqResponseDTO> getAll() {
        List<CifjoinqqResponseDTO> cifjoinqqResponseDTOs = null;
        List<Cifjoinqq> cifjoinqqs = cifjoinqqRepository.findAll();

        if (!cifjoinqqs.isEmpty()) {
            cifjoinqqResponseDTOs = new ArrayList<>();
            for (Cifjoinqq cifjoinqq : cifjoinqqs) {
                CifjoinqqResponseDTO cifjoinqqResponseDTO = new CifjoinqqResponseDTO(cifjoinqq);
                cifjoinqqResponseDTOs.add(cifjoinqqResponseDTO);
            }

        }

        return cifjoinqqResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CifjoinqqResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Cifjoinqq> dataTablesOutput = cifjoinqqRepository.findAll(dataTablesInput);

        List<CifjoinqqResponseDTO> cifjoinqqResponseDTOS = new ArrayList<>();
        for (Cifjoinqq cifjoinqq : dataTablesOutput.getData()) {
            CifjoinqqResponseDTO cifjoinqqResponseDTO = new CifjoinqqResponseDTO(cifjoinqq);

            cifjoinqqResponseDTOS.add(cifjoinqqResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(cifjoinqqResponseDTOS);

        return result;
    }

    public CifjoinqqResponseDTO findById(String id) {
        CifjoinqqResponseDTO cifjoinqqResponseDTO = null;
        Optional<Cifjoinqq> cifjoinqqOpt = cifjoinqqRepository.findById(id);
        if (cifjoinqqOpt.isPresent()) {
            Cifjoinqq cifjoinqq = cifjoinqqOpt.get();

            cifjoinqqResponseDTO = new CifjoinqqResponseDTO(cifjoinqq);
        }

        return cifjoinqqResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Cifjoinqq> cifjoinqqOpt = cifjoinqqRepository.findById(id);
        if (cifjoinqqOpt.isPresent()) {
            cifjoinqqRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Cifjoinqq> cifjoinqqs = cifjoinqqRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.cifjoinqqToExcel(cifjoinqqs);
        return in;
    }
}
