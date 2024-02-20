package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.LiabilitasLainnyaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.LiabilitasLainnyaResponseDTO;
import id.co.telkomsigma.portalapps.model.LiabilitasLainnya;
import id.co.telkomsigma.portalapps.repository.LiabilitasLainnyaRepository;
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
public class LiabilitasLainnyaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LiabilitasLainnyaService.class);

    private LiabilitasLainnyaRepository liabilitasLainnyaRepository;

    @Autowired
    public LiabilitasLainnyaService(
            LiabilitasLainnyaRepository liabilitasLainnyaRepository
    ) {
        this.liabilitasLainnyaRepository = liabilitasLainnyaRepository;
    }

    public void save(LiabilitasLainnyaRequestDTO liabilitasLainnyaRequestDTO) {
        LiabilitasLainnya liabilitasLainnya = null;
        Optional<LiabilitasLainnya> liabilitasLainnyaOpt = liabilitasLainnyaRepository.findById(liabilitasLainnyaRequestDTO.getKodeCoa());

        if (liabilitasLainnyaRequestDTO.isNew()) {
            // do insert
            if (liabilitasLainnyaOpt.isPresent()) {
                throw new DuplicateDataException("Kode COA you entered already exist!");
            } else {
                liabilitasLainnya = new LiabilitasLainnya(liabilitasLainnyaRequestDTO);
            }
        } else {
            // do update
            liabilitasLainnya = liabilitasLainnyaOpt.get();
            liabilitasLainnya.setKeterangan(liabilitasLainnyaRequestDTO.getKeterangan());
            liabilitasLainnya.setJenisLiabilitas(liabilitasLainnyaRequestDTO.getJenisLiabilitas());
            liabilitasLainnya.setGolonganKreditur(liabilitasLainnyaRequestDTO.getGolonganKreditur());
            liabilitasLainnya.setHubunganKreditur(liabilitasLainnyaRequestDTO.getHubunganKreditur());
            liabilitasLainnya.setNegaraKreditur(liabilitasLainnyaRequestDTO.getNegaraKreditur());
            liabilitasLainnya.setPendBknPend(liabilitasLainnyaRequestDTO.getPendBknPend());
        }

        liabilitasLainnyaRepository.save(liabilitasLainnya);
        LOGGER.info("Successfully save application with id: " + liabilitasLainnyaRequestDTO.getKodeCoa());
    }

    public List<LiabilitasLainnyaResponseDTO> getAll() {
        List<LiabilitasLainnyaResponseDTO> liabilitasLainnyaResponseDTOs = null;
        List<LiabilitasLainnya> liabilitasLainnyas = liabilitasLainnyaRepository.findAll();

        if (!liabilitasLainnyas.isEmpty()) {
            liabilitasLainnyaResponseDTOs = new ArrayList<>();
            for (LiabilitasLainnya liabilitasLainnya : liabilitasLainnyas) {
                LiabilitasLainnyaResponseDTO liabilitasLainnyaResponseDTO = new LiabilitasLainnyaResponseDTO(liabilitasLainnya);
                liabilitasLainnyaResponseDTOs.add(liabilitasLainnyaResponseDTO);
            }

        }

        return liabilitasLainnyaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<LiabilitasLainnyaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<LiabilitasLainnya> dataTablesOutput = liabilitasLainnyaRepository.findAll(dataTablesInput);

        List<LiabilitasLainnyaResponseDTO> liabilitasAntarKantorResponseDTOS = new ArrayList<>();
        for (LiabilitasLainnya liabilitasLainnya : dataTablesOutput.getData()) {
            LiabilitasLainnyaResponseDTO liabilitasLainnyaResponseDTO = new LiabilitasLainnyaResponseDTO(liabilitasLainnya);

            liabilitasAntarKantorResponseDTOS.add(liabilitasLainnyaResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(liabilitasAntarKantorResponseDTOS);

        return result;
    }

    public LiabilitasLainnyaResponseDTO findById(String id) {
        LiabilitasLainnyaResponseDTO liabilitasLainnyaResponseDTO = null;
        Optional<LiabilitasLainnya> liabilitasLainnyaOpt = liabilitasLainnyaRepository.findById(id);
        if (liabilitasLainnyaOpt.isPresent()) {
            LiabilitasLainnya liabilitasLainnya = liabilitasLainnyaOpt.get();

            liabilitasLainnyaResponseDTO = new LiabilitasLainnyaResponseDTO(liabilitasLainnya);
        }

        return liabilitasLainnyaResponseDTO;
    }

    public void deleteById(String id) {
        Optional<LiabilitasLainnya> liabilitasLainnyaOpt = liabilitasLainnyaRepository.findById(id);
        if (liabilitasLainnyaOpt.isPresent()) {
            liabilitasLainnyaRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<LiabilitasLainnya> liabilitasLainnyas = liabilitasLainnyaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.liabilitasLainnyaToExcel(liabilitasLainnyas);
        return in;
    }
}
