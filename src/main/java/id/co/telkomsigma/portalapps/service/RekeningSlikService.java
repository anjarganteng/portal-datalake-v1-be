package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.RekeningSlikRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.RekeningSlikResponseDTO;
import id.co.telkomsigma.portalapps.model.RekeningSlik;
import id.co.telkomsigma.portalapps.repository.RekeningSlikRepository;
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
public class RekeningSlikService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RekeningSlikService.class);

    private RekeningSlikRepository rekeningSlikRepository;

    @Autowired
    public RekeningSlikService(
            RekeningSlikRepository rekeningSlikRepository
    ) {
        this.rekeningSlikRepository = rekeningSlikRepository;
    }

    public void save(RekeningSlikRequestDTO rekeningSlikRequestDTO) {
        RekeningSlik rekeningSlik = null;
        Optional<RekeningSlik> rekeningSlikOpt = rekeningSlikRepository.findById(rekeningSlikRequestDTO.getNoValid());

        if (rekeningSlikRequestDTO.isNew()) {
            // do insert
            if (rekeningSlikOpt.isPresent()) {
                throw new DuplicateDataException("No Valid you entered already exist!");
            } else {
                rekeningSlik = new RekeningSlik(rekeningSlikRequestDTO);
            }
        } else {
            // do update
            rekeningSlik = rekeningSlikOpt.get();
            rekeningSlik.setNoSlik(rekeningSlikRequestDTO.getNoSlik());

        }

        rekeningSlikRepository.save(rekeningSlik);
        LOGGER.info("Successfully save application with id: " + rekeningSlikRequestDTO.getNoValid());
    }

    public List<RekeningSlikResponseDTO> getAll() {
        List<RekeningSlikResponseDTO> rekeningSlikResponseDTOs = null;
        List<RekeningSlik> rekeningSliks = rekeningSlikRepository.findAll();

        if (!rekeningSliks.isEmpty()) {
            rekeningSlikResponseDTOs = new ArrayList<>();
            for (RekeningSlik rekeningSlik : rekeningSliks) {
                RekeningSlikResponseDTO rekeningSlikResponseDTO = new RekeningSlikResponseDTO(rekeningSlik);
                rekeningSlikResponseDTOs.add(rekeningSlikResponseDTO);
            }

        }

        return rekeningSlikResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<RekeningSlikResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<RekeningSlik> dataTablesOutput = rekeningSlikRepository.findAll(dataTablesInput);

        List<RekeningSlikResponseDTO> rekeningSlikResponseDTOS = new ArrayList<>();
        for (RekeningSlik rekeningSlik : dataTablesOutput.getData()) {
            RekeningSlikResponseDTO rekeningSlikResponseDTO = new RekeningSlikResponseDTO(rekeningSlik);

            rekeningSlikResponseDTOS.add(rekeningSlikResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(rekeningSlikResponseDTOS);

        return result;
    }

    public RekeningSlikResponseDTO findById(String id) {
        RekeningSlikResponseDTO rekeningSlikResponseDTO = null;
        Optional<RekeningSlik> rekeningSlikOpt = rekeningSlikRepository.findById(id);
        if (rekeningSlikOpt.isPresent()) {
            RekeningSlik rekeningSlik = rekeningSlikOpt.get();

            rekeningSlikResponseDTO = new RekeningSlikResponseDTO(rekeningSlik);
        }

        return rekeningSlikResponseDTO;
    }

    public void deleteById(String id) {
        Optional<RekeningSlik> rekeningSlikOpt = rekeningSlikRepository.findById(id);
        if (rekeningSlikOpt.isPresent()) {
            rekeningSlikRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<RekeningSlik> rekeningSliks = rekeningSlikRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.rekeningSlikToExcel(rekeningSliks);
        return in;
    }
}
