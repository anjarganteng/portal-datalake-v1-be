package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.KodeTransaksiKasValasRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.KodeTransaksiKasValasResponseDTO;
import id.co.telkomsigma.portalapps.model.KodeTransaksiKasValas;
import id.co.telkomsigma.portalapps.repository.KodeTransaksiKasValasRepository;
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
public class KodeTransaksiKasValasService {

    public static final Logger LOGGER = LoggerFactory.getLogger(KodeTransaksiKasValasService.class);

    private KodeTransaksiKasValasRepository kodeTransaksiKasValasRepository;

    @Autowired
    public KodeTransaksiKasValasService(
            KodeTransaksiKasValasRepository kodeTransaksiKasValasRepository
    ) {
        this.kodeTransaksiKasValasRepository = kodeTransaksiKasValasRepository;
    }

    public void save(KodeTransaksiKasValasRequestDTO kodeTransaksiKasValasRequestDTO) {
        KodeTransaksiKasValas kodeTransaksiKasValas = null;
        Optional<KodeTransaksiKasValas> kodeTransaksiKasValasOpt = kodeTransaksiKasValasRepository.findById(kodeTransaksiKasValasRequestDTO.getKodeTransaksi());

        if (kodeTransaksiKasValasRequestDTO.isNew()) {
            //do insert
            if (kodeTransaksiKasValasOpt.isPresent()) {
                throw new DuplicateDataException("Kode Transaksi you entered already exist!");
            } else {
                kodeTransaksiKasValas = new KodeTransaksiKasValas(kodeTransaksiKasValasRequestDTO);
            }
        } else {
            //do update
            kodeTransaksiKasValas = kodeTransaksiKasValasOpt.get();
            kodeTransaksiKasValas.setDeskripsi(kodeTransaksiKasValasRequestDTO.getDeskripsi());
            kodeTransaksiKasValas.setFlagDebet(kodeTransaksiKasValasRequestDTO.getFlagDebet());
            kodeTransaksiKasValas.setFlagKredit(kodeTransaksiKasValasRequestDTO.getFlagKredit());
        }

        KodeTransaksiKasValas saved = kodeTransaksiKasValasRepository.save(kodeTransaksiKasValas);
        LOGGER.info("Successfully save application with id: " + saved.getKodeTransaksi());
    }

    public List<KodeTransaksiKasValasResponseDTO> getAll() {
        List<KodeTransaksiKasValasResponseDTO> kodeTransaksiKasValasResponseDTOs = null;
        List<KodeTransaksiKasValas> kodeTransaksiKasValass = kodeTransaksiKasValasRepository.findAll();

        if (!kodeTransaksiKasValass.isEmpty()) {
            kodeTransaksiKasValasResponseDTOs = new ArrayList<>();
            for (KodeTransaksiKasValas kodeTransaksiKasValas : kodeTransaksiKasValass) {
                KodeTransaksiKasValasResponseDTO kodeTransaksiKasValasResponseDTO = new KodeTransaksiKasValasResponseDTO(kodeTransaksiKasValas);
                kodeTransaksiKasValasResponseDTOs.add(kodeTransaksiKasValasResponseDTO);
            }

        }

        return kodeTransaksiKasValasResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<KodeTransaksiKasValasResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<KodeTransaksiKasValas> dataTablesOutput = kodeTransaksiKasValasRepository.findAll(dataTablesInput);

        List<KodeTransaksiKasValasResponseDTO> kodeTransaksiKasValasResponseDTOS = new ArrayList<>();
        for (KodeTransaksiKasValas kodeTransaksiKasValas : dataTablesOutput.getData()) {
            KodeTransaksiKasValasResponseDTO kodeTransaksiKasValasResponseDTO = new KodeTransaksiKasValasResponseDTO(kodeTransaksiKasValas);

            kodeTransaksiKasValasResponseDTOS.add(kodeTransaksiKasValasResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(kodeTransaksiKasValasResponseDTOS);

        return result;
    }

    public KodeTransaksiKasValasResponseDTO findById(String id) {
        KodeTransaksiKasValasResponseDTO kodeTransaksiKasValasResponseDTO = null;
        Optional<KodeTransaksiKasValas> kodeTransaksiKasValasOpt = kodeTransaksiKasValasRepository.findById(id);
        if (kodeTransaksiKasValasOpt.isPresent()) {
            KodeTransaksiKasValas kodeTransaksiKasValas = kodeTransaksiKasValasOpt.get();

            kodeTransaksiKasValasResponseDTO = new KodeTransaksiKasValasResponseDTO(kodeTransaksiKasValas);
        }

        return kodeTransaksiKasValasResponseDTO;
    }

    public void deleteById(String id) {
        Optional<KodeTransaksiKasValas> kodeTransaksiKasValasOpt = kodeTransaksiKasValasRepository.findById(id);
        if (kodeTransaksiKasValasOpt.isPresent()) {
            kodeTransaksiKasValasRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<KodeTransaksiKasValas> kodeTransaksiKasValass = kodeTransaksiKasValasRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.kasValasToExcel(kodeTransaksiKasValass);
        return in;
    }
}
