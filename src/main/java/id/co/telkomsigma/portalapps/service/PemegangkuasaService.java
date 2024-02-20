package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PemegangkuasaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PemegangkuasaResponseDTO;
import id.co.telkomsigma.portalapps.model.Pemegangkuasa;
import id.co.telkomsigma.portalapps.repository.PemegangkuasaRepository;
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
public class PemegangkuasaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PemegangkuasaService.class);

    private PemegangkuasaRepository pemegangkuasaRepository;

    @Autowired
    public PemegangkuasaService(
            PemegangkuasaRepository pemegangkuasaRepository
    ) {
        this.pemegangkuasaRepository = pemegangkuasaRepository;
    }

    public void save(PemegangkuasaRequestDTO pemegangkuasaRequestDTO) {
        Pemegangkuasa pemegangkuasa = null;
        Optional<Pemegangkuasa> pemegangkuasaOpt = pemegangkuasaRepository.findById(pemegangkuasaRequestDTO.getNoCif());

        if (pemegangkuasaRequestDTO.isNew()) {
            // do insert
            if (pemegangkuasaOpt.isPresent()) {
                throw new DuplicateDataException("NO CIF you entered already exist!");
            } else {
                pemegangkuasa = new Pemegangkuasa(pemegangkuasaRequestDTO);
                if (pemegangkuasaRequestDTO.getJenisId().equals("KTP")) {
                    if (this.validationKtp(pemegangkuasaRequestDTO.getNomorId())) {
                        ;
                    } else {
                        throw new RuntimeException("Check your input KTP");
                    }
                }
            }
        } else {
            // do update
            pemegangkuasa = pemegangkuasaOpt.get();
            pemegangkuasa.setJenisId(pemegangkuasaRequestDTO.getJenisId());
            pemegangkuasa.setNomorId(pemegangkuasaRequestDTO.getNomorId());
        }

        pemegangkuasaRepository.save(pemegangkuasa);
        LOGGER.info("Successfully save application with id: " + pemegangkuasaRequestDTO.getNoCif());

    }

    public List<PemegangkuasaResponseDTO> getAll() {
        List<PemegangkuasaResponseDTO> pemegangkuasaResponseDTOs = null;
        List<Pemegangkuasa> pemegangkuasas = pemegangkuasaRepository.findAll();

        if (!pemegangkuasas.isEmpty()) {
            pemegangkuasaResponseDTOs = new ArrayList<>();
            for (Pemegangkuasa pemegangkuasa : pemegangkuasas) {
                PemegangkuasaResponseDTO pemegangkuasaResponseDTO = new PemegangkuasaResponseDTO(pemegangkuasa);
                pemegangkuasaResponseDTOs.add(pemegangkuasaResponseDTO);
            }

        }

        return pemegangkuasaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PemegangkuasaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Pemegangkuasa> dataTablesOutput = pemegangkuasaRepository.findAll(dataTablesInput);

        List<PemegangkuasaResponseDTO> pemegangkuasaResponseDTOS = new ArrayList<>();
        for (Pemegangkuasa pemegangkuasa : dataTablesOutput.getData()) {
            PemegangkuasaResponseDTO pemegangkuasaResponseDTO = new PemegangkuasaResponseDTO(pemegangkuasa);

            pemegangkuasaResponseDTOS.add(pemegangkuasaResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(pemegangkuasaResponseDTOS);

        return result;
    }

    public PemegangkuasaResponseDTO findById(String id) {
        PemegangkuasaResponseDTO pemegangkuasaResponseDTO = null;
        Optional<Pemegangkuasa> pemegangkuasaOpt = pemegangkuasaRepository.findById(id);
        if (pemegangkuasaOpt.isPresent()) {
            Pemegangkuasa pemegangkuasa = pemegangkuasaOpt.get();

            pemegangkuasaResponseDTO = new PemegangkuasaResponseDTO(pemegangkuasa);
        }

        return pemegangkuasaResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Pemegangkuasa> pemegangkuasaOpt = pemegangkuasaRepository.findById(id);
        if (pemegangkuasaOpt.isPresent()) {
            pemegangkuasaRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Pemegangkuasa> pemegangkuasas = pemegangkuasaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.pemegangkuasaToExcel(pemegangkuasas);
        return in;
    }

    public boolean validationKtp(String ktp) {
        boolean checkOnlyNumber = ktp.matches("[0-9]+");

        if (!checkOnlyNumber) {
            return false;
        }

        int length = ktp.length();

        if (length != 16) {
            return false;
        }

        return true;
    }
}
