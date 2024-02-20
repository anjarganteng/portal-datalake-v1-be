package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.AtiRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.AtiResponseDTO;
import id.co.telkomsigma.portalapps.model.Ati;
import id.co.telkomsigma.portalapps.repository.AtiRepository;
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
public class AtiService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AtiService.class);

    private AtiRepository atiRepository;

    @Autowired
    public AtiService(
            AtiRepository atiRepository
    ) {
        this.atiRepository = atiRepository;
    }

    public void save(AtiRequestDTO atiRequestDTO) {
        Ati ati = null;
        Optional<Ati> atiOpt = atiRepository.findById(atiRequestDTO.getNomorAset());

        if (atiRequestDTO.isNew()) {
            //do insert
            if (atiOpt.isPresent()) {
                throw new DuplicateDataException("No Aset you entered already exist!");
            } else {
                ati = new Ati(atiRequestDTO);
            }
        } else {
            //do update
            ati = atiOpt.get();
            ati.setJenisAset(atiRequestDTO.getJenisAset());
            ati.setTglPerolehan(atiRequestDTO.getTglPerolehan());
            ati.setJenisValuta(atiRequestDTO.getJenisValuta());
            ati.setSumberPerolehan(atiRequestDTO.getSumberPerolehan());
            ati.setMetodeUkur(atiRequestDTO.getMetodeUkur());
            ati.setStatusAset(atiRequestDTO.getStatusAset());
            ati.setJmlBlnLalu(atiRequestDTO.getJmlBlnLalu());
            ati.setJmlDebet(atiRequestDTO.getJmlDebet());
            ati.setJmlKredit(atiRequestDTO.getJmlKredit());
            ati.setJmlLainnya(atiRequestDTO.getJmlLainnya());
            ati.setJmlBlnLaporan(atiRequestDTO.getJmlBlnLaporan());
            ati.setAkumulasiSusut(atiRequestDTO.getAkumulasiSusut());
        }

        Ati saved = atiRepository.save(ati);
        LOGGER.info("Successfully save application with id: " + saved.getNomorAset());
    }

    public List<AtiResponseDTO> getAll() {
        List<AtiResponseDTO> atiResponseDTOs = null;
        List<Ati> atis = atiRepository.findAll();

        if (!atis.isEmpty()) {
            atiResponseDTOs = new ArrayList<>();
            for (Ati ati : atis) {
                AtiResponseDTO atiResponseDTO = new AtiResponseDTO(ati);
                atiResponseDTOs.add(atiResponseDTO);
            }

        }

        return atiResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<AtiResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Ati> dataTablesOutput = atiRepository.findAll(dataTablesInput);

        List<AtiResponseDTO> atiResponseDTOS = new ArrayList<>();
        for (Ati ati : dataTablesOutput.getData()) {
            AtiResponseDTO atiResponseDTO = new AtiResponseDTO(ati);

            atiResponseDTOS.add(atiResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(atiResponseDTOS);

        return result;
    }

    public AtiResponseDTO findById(String id) {
        AtiResponseDTO atiResponseDTO = null;
        Optional<Ati> atiOpt = atiRepository.findById(id);
        if (atiOpt.isPresent()) {
            Ati ati = atiOpt.get();

            atiResponseDTO = new AtiResponseDTO(ati);
        }

        return atiResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Ati> atiOpt = atiRepository.findById(id);
        if (atiOpt.isPresent()) {
            atiRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Ati> atis = atiRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.atiToExcel(atis);
        return in;
    }
}
