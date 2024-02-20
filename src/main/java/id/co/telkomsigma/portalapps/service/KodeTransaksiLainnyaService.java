package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.KodeTransaksiLainnyaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.KodeTransaksiLainnyaResponseDTO;
import id.co.telkomsigma.portalapps.model.KodeTransaksiLainnya;
import id.co.telkomsigma.portalapps.repository.KodeTransaksiLainnyaRepository;
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
public class KodeTransaksiLainnyaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(KodeTransaksiLainnyaService.class);

    private KodeTransaksiLainnyaRepository kodeTransaksiLainnyaRepository;

    @Autowired
    public KodeTransaksiLainnyaService(
            KodeTransaksiLainnyaRepository kodeTransaksiLainnyaRepository
    ) {
        this.kodeTransaksiLainnyaRepository = kodeTransaksiLainnyaRepository;
    }

    public void save(KodeTransaksiLainnyaRequestDTO kodeTransaksiLainnyaRequestDTO) {
        KodeTransaksiLainnya kodeTransaksiLainnya = null;
        Optional<KodeTransaksiLainnya> kodeTransaksiLainnyaOpt = kodeTransaksiLainnyaRepository.findById(kodeTransaksiLainnyaRequestDTO.getKodeTransaksi());

        if (kodeTransaksiLainnyaOpt.isPresent()) {
            kodeTransaksiLainnya = kodeTransaksiLainnyaRepository.findById(kodeTransaksiLainnyaRequestDTO.getKodeTransaksi()).get();
            kodeTransaksiLainnya.setKodeTransaksi(kodeTransaksiLainnyaRequestDTO.getKodeTransaksi());
        } else {
            kodeTransaksiLainnya = new KodeTransaksiLainnya(kodeTransaksiLainnyaRequestDTO);
        }

        KodeTransaksiLainnya saved = kodeTransaksiLainnyaRepository.save(kodeTransaksiLainnya);
        LOGGER.info("Successfully save application with id: " + saved.getKodeTransaksi());
    }

    public List<KodeTransaksiLainnyaResponseDTO> getAll() {
        List<KodeTransaksiLainnyaResponseDTO> kodeTransaksiLainnyaResponseDTOs = null;
        List<KodeTransaksiLainnya> kodeTransaksiLainnyas = kodeTransaksiLainnyaRepository.findAll();

        if (!kodeTransaksiLainnyas.isEmpty()) {
            kodeTransaksiLainnyaResponseDTOs = new ArrayList<>();
            for (KodeTransaksiLainnya kodeTransaksiLainnya : kodeTransaksiLainnyas) {
                KodeTransaksiLainnyaResponseDTO kodeTransaksiLainnyaResponseDTO = new KodeTransaksiLainnyaResponseDTO(kodeTransaksiLainnya);
                kodeTransaksiLainnyaResponseDTOs.add(kodeTransaksiLainnyaResponseDTO);
            }

        }

        return kodeTransaksiLainnyaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<KodeTransaksiLainnyaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<KodeTransaksiLainnya> dataTablesOutput = kodeTransaksiLainnyaRepository.findAll(dataTablesInput);

        List<KodeTransaksiLainnyaResponseDTO> kodeTransaksiLainnyaResponseDTOS = new ArrayList<>();
        for (KodeTransaksiLainnya kodeTransaksiLainnya : dataTablesOutput.getData()) {
            KodeTransaksiLainnyaResponseDTO kodeTransaksiLainnyaResponseDTO = new KodeTransaksiLainnyaResponseDTO(kodeTransaksiLainnya);

            kodeTransaksiLainnyaResponseDTOS.add(kodeTransaksiLainnyaResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(kodeTransaksiLainnyaResponseDTOS);

        return result;
    }

    public KodeTransaksiLainnyaResponseDTO findById(String id) {
        KodeTransaksiLainnyaResponseDTO kodeTransaksiLainnyaResponseDTO = null;
        Optional<KodeTransaksiLainnya> kodeTransaksiLainnyaOpt = kodeTransaksiLainnyaRepository.findById(id);
        if (kodeTransaksiLainnyaOpt.isPresent()) {
            KodeTransaksiLainnya kodeTransaksiLainnya = kodeTransaksiLainnyaOpt.get();

            kodeTransaksiLainnyaResponseDTO = new KodeTransaksiLainnyaResponseDTO(kodeTransaksiLainnya);
        }

        return kodeTransaksiLainnyaResponseDTO;
    }

    public void deleteById(String id) {
        Optional<KodeTransaksiLainnya> kodeTransaksiLainnyaOpt = kodeTransaksiLainnyaRepository.findById(id);
        if (kodeTransaksiLainnyaOpt.isPresent()) {
            kodeTransaksiLainnyaRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<KodeTransaksiLainnya> kodeTransaksiLainnyas = kodeTransaksiLainnyaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.kodeTransaksiLainnyaToExcel(kodeTransaksiLainnyas);
        return in;
    }
}
