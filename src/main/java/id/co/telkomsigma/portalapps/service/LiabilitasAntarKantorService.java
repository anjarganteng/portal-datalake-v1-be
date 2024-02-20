package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.LiabilitasAntarKantorRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.LiabilitasAntarKantorResponseDTO;
import id.co.telkomsigma.portalapps.model.LiabilitasAntarKantor;
import id.co.telkomsigma.portalapps.repository.LiabilitasAntarKantorRepository;
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
public class LiabilitasAntarKantorService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LiabilitasAntarKantorService.class);

    private LiabilitasAntarKantorRepository liabilitasAntarKantorRepository;

    @Autowired
    public LiabilitasAntarKantorService(
            LiabilitasAntarKantorRepository liabilitasAntarKantorRepository
    ) {
        this.liabilitasAntarKantorRepository = liabilitasAntarKantorRepository;
    }

    public void save(LiabilitasAntarKantorRequestDTO liabilitasAntarKantorRequestDTO) {
        LiabilitasAntarKantor liabilitasAntarKantor = null;
        Optional<LiabilitasAntarKantor> liabilitasAntarKantorOpt = liabilitasAntarKantorRepository.findById(liabilitasAntarKantorRequestDTO.getKodeCoa());

        if (liabilitasAntarKantorRequestDTO.isNew()) {
            // do insert
            if (liabilitasAntarKantorOpt.isPresent()) {
                throw new DuplicateDataException("Kode COA you entered already exist!");
            } else {
                liabilitasAntarKantor = new LiabilitasAntarKantor(liabilitasAntarKantorRequestDTO);
            }
        } else {
            // do update
            liabilitasAntarKantor = liabilitasAntarKantorOpt.get();
            liabilitasAntarKantor.setDeskripsiCoa(liabilitasAntarKantorRequestDTO.getDeskripsiCoa());
            liabilitasAntarKantor.setUsahaKantor(liabilitasAntarKantorRequestDTO.getUsahaKantor());
            liabilitasAntarKantor.setStatusKantor(liabilitasAntarKantorRequestDTO.getStatusKantor());
            liabilitasAntarKantor.setNegaraKantor(liabilitasAntarKantorRequestDTO.getNegaraKantor());
            liabilitasAntarKantor.setJenisLiabilitas(liabilitasAntarKantorRequestDTO.getJenisLiabilitas());
            liabilitasAntarKantor.setPendBknPend(liabilitasAntarKantorRequestDTO.getPendBknPend());
            liabilitasAntarKantor.setSukuBungaRp(liabilitasAntarKantorRequestDTO.getSukuBungaRp());
            liabilitasAntarKantor.setSukuBungaVl(liabilitasAntarKantorRequestDTO.getSukuBungaVl());
        }

        liabilitasAntarKantorRepository.save(liabilitasAntarKantor);
        LOGGER.info("Successfully save application with id: " + liabilitasAntarKantorRequestDTO.getKodeCoa());
    }

    public List<LiabilitasAntarKantorResponseDTO> getAll() {
        List<LiabilitasAntarKantorResponseDTO> liabilitasAntarKantorResponseDTOs = null;
        List<LiabilitasAntarKantor> liabilitasAntarKantors = liabilitasAntarKantorRepository.findAll();

        if (!liabilitasAntarKantors.isEmpty()) {
            liabilitasAntarKantorResponseDTOs = new ArrayList<>();
            for (LiabilitasAntarKantor liabilitasAntarKantor : liabilitasAntarKantors) {
                LiabilitasAntarKantorResponseDTO liabilitasAntarKantorResponseDTO = new LiabilitasAntarKantorResponseDTO(liabilitasAntarKantor);
                liabilitasAntarKantorResponseDTOs.add(liabilitasAntarKantorResponseDTO);
            }

        }

        return liabilitasAntarKantorResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<LiabilitasAntarKantorResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<LiabilitasAntarKantor> dataTablesOutput = liabilitasAntarKantorRepository.findAll(dataTablesInput);

        List<LiabilitasAntarKantorResponseDTO> liabilitasAntarKantorResponseDTOS = new ArrayList<>();
        for (LiabilitasAntarKantor liabilitasAntarKantor : dataTablesOutput.getData()) {
            LiabilitasAntarKantorResponseDTO liabilitasAntarKantorResponseDTO = new LiabilitasAntarKantorResponseDTO(liabilitasAntarKantor);

            liabilitasAntarKantorResponseDTOS.add(liabilitasAntarKantorResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(liabilitasAntarKantorResponseDTOS);

        return result;
    }

    public LiabilitasAntarKantorResponseDTO findById(String id) {
        LiabilitasAntarKantorResponseDTO liabilitasAntarKantorResponseDTO = null;
        Optional<LiabilitasAntarKantor> liabilitasAntarKantorOpt = liabilitasAntarKantorRepository.findById(id);
        if (liabilitasAntarKantorOpt.isPresent()) {
            LiabilitasAntarKantor liabilitasAntarKantor = liabilitasAntarKantorOpt.get();

            liabilitasAntarKantorResponseDTO = new LiabilitasAntarKantorResponseDTO(liabilitasAntarKantor);
        }

        return liabilitasAntarKantorResponseDTO;
    }

    public void deleteById(String id) {
        Optional<LiabilitasAntarKantor> liabilitasAntarKantorOpt = liabilitasAntarKantorRepository.findById(id);
        if (liabilitasAntarKantorOpt.isPresent()) {
            liabilitasAntarKantorRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<LiabilitasAntarKantor> liabilitasAntarKantors = liabilitasAntarKantorRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.liabilitasAntarKantorToExcel(liabilitasAntarKantors);
        return in;
    }
}
