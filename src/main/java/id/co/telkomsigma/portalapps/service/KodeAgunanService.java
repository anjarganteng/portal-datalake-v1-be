package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.KodeAgunanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.KodeAgunanResponseDTO;
import id.co.telkomsigma.portalapps.model.KodeAgunan;
import id.co.telkomsigma.portalapps.repository.KodeAgunanRepository;
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
public class KodeAgunanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(KodeAgunanService.class);

    private KodeAgunanRepository kodeAgunanRepository;

    @Autowired
    public KodeAgunanService(
            KodeAgunanRepository kodeAgunanRepository
    ) {
        this.kodeAgunanRepository = kodeAgunanRepository;
    }

    public void save(KodeAgunanRequestDTO kodeAgunanRequestDTO) {
        KodeAgunan kodeAgunan = null;
        Optional<KodeAgunan> kodeAgunanOpt = kodeAgunanRepository.findById(kodeAgunanRequestDTO.getKodeAgunanCore());

        if (kodeAgunanRequestDTO.isNew()) {
            //do insert
            if (kodeAgunanOpt.isPresent()) {
                throw new DuplicateDataException("Kode Agunan Core you entered already exist!");
            } else {
                kodeAgunan = new KodeAgunan(kodeAgunanRequestDTO);
            }
        } else {
            //do update
            kodeAgunan = kodeAgunanOpt.get();
            kodeAgunan.setKeterangan(kodeAgunanRequestDTO.getKeterangan());
            kodeAgunan.setNilaiPersentase(kodeAgunanRequestDTO.getNilaiPersentase());
            kodeAgunan.setJw1(kodeAgunanRequestDTO.getJw1());
            kodeAgunan.setPersen1(kodeAgunanRequestDTO.getPersen1());
            kodeAgunan.setJw2(kodeAgunanRequestDTO.getJw2());
            kodeAgunan.setPersen2(kodeAgunanRequestDTO.getPersen2());
            kodeAgunan.setJw3(kodeAgunanRequestDTO.getJw3());
            kodeAgunan.setPersen3(kodeAgunanRequestDTO.getPersen3());
            kodeAgunan.setJw4(kodeAgunanRequestDTO.getJw4());
            kodeAgunan.setPersen4(kodeAgunanRequestDTO.getPersen4());
            kodeAgunan.setJw5(kodeAgunanRequestDTO.getJw5());
            kodeAgunan.setPersen5(kodeAgunanRequestDTO.getPersen5());
            kodeAgunan.setJw6(kodeAgunanRequestDTO.getJw6());
            kodeAgunan.setPersen6(kodeAgunanRequestDTO.getPersen6());
            kodeAgunan.setJw7(kodeAgunanRequestDTO.getJw7());
            kodeAgunan.setPersen7(kodeAgunanRequestDTO.getPersen7());
        }

        KodeAgunan saved = kodeAgunanRepository.save(kodeAgunan);
        LOGGER.info("Successfully save application with id: " + saved.getKodeAgunanCore());
    }

    public List<KodeAgunanResponseDTO> getAll() {
        List<KodeAgunanResponseDTO> kodeAgunanResponseDTOs = null;
        List<KodeAgunan> kodeAgunans = kodeAgunanRepository.findAll();

        if (!kodeAgunans.isEmpty()) {
            kodeAgunanResponseDTOs = new ArrayList<>();
            for (KodeAgunan kodeAgunan : kodeAgunans) {
                KodeAgunanResponseDTO kodeAgunanResponseDTO = new KodeAgunanResponseDTO(kodeAgunan);
                kodeAgunanResponseDTOs.add(kodeAgunanResponseDTO);
            }

        }

        return kodeAgunanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<KodeAgunanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<KodeAgunan> dataTablesOutput = kodeAgunanRepository.findAll(dataTablesInput);

        List<KodeAgunanResponseDTO> kodeAgunanResponseDTOS = new ArrayList<>();
        for (KodeAgunan kodeAgunan : dataTablesOutput.getData()) {
            KodeAgunanResponseDTO kodeAgunanResponseDTO = new KodeAgunanResponseDTO(kodeAgunan);

            kodeAgunanResponseDTOS.add(kodeAgunanResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(kodeAgunanResponseDTOS);

        return result;
    }

    public KodeAgunanResponseDTO findById(String id) {
        KodeAgunanResponseDTO kodeAgunanResponseDTO = null;
        Optional<KodeAgunan> kodeAgunanOpt = kodeAgunanRepository.findById(id);
        if (kodeAgunanOpt.isPresent()) {
            KodeAgunan kodeAgunan = kodeAgunanOpt.get();

            kodeAgunanResponseDTO = new KodeAgunanResponseDTO(kodeAgunan);
        }

        return kodeAgunanResponseDTO;
    }

    public void deleteById(String id) {
        Optional<KodeAgunan> kodeAgunanOpt = kodeAgunanRepository.findById(id);
        if (kodeAgunanOpt.isPresent()) {
            kodeAgunanRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<KodeAgunan> kodeAgunans = kodeAgunanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.kodeAgunanToExcel(kodeAgunans);
        return in;
    }
}
