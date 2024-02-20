package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CabangPelaporRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CabangPelaporResponseDTO;
import id.co.telkomsigma.portalapps.model.CabangPelapor;
import id.co.telkomsigma.portalapps.repository.CabangPelaporRepository;
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
public class CabangPelaporService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CabangPelaporRepository.class);

    private CabangPelaporRepository cabangPelaporRepository;

    @Autowired
    public CabangPelaporService(
            CabangPelaporRepository cabangPelaporRepository
    ) {
        this.cabangPelaporRepository = cabangPelaporRepository;
    }

    public void save(CabangPelaporRequestDTO cabangPelaporRequestDTO) {
        CabangPelapor cabangPelapor = null;
        Optional<CabangPelapor> cabangPelaporOpt = cabangPelaporRepository.findById(cabangPelaporRequestDTO.getKodeCabang());

        if (cabangPelaporRequestDTO.isNew()) {
            // do insert
            if (cabangPelaporOpt.isPresent()) {
                throw new DuplicateDataException("Kode Cabang you entered already exist!");
            } else {
                cabangPelapor = new CabangPelapor(cabangPelaporRequestDTO);
            }
        } else {
            // do update
            cabangPelapor = cabangPelaporOpt.get();
            cabangPelapor.setStatusData(cabangPelaporRequestDTO.getStatusData());
            cabangPelapor.setKodeCabangUtama(cabangPelaporRequestDTO.getKodeCabangUtama());
            cabangPelapor.setNamaCabang(cabangPelaporRequestDTO.getNamaCabang());
            cabangPelapor.setAliasCabang(cabangPelaporRequestDTO.getAliasCabang());
            cabangPelapor.setKodeCoa(cabangPelaporRequestDTO.getKodeCoa());
            cabangPelapor.setAliasWilayah(cabangPelaporRequestDTO.getAliasWilayah());
            cabangPelapor.setKodeCabBi(cabangPelaporRequestDTO.getKodeCabBi());
        }

        cabangPelaporRepository.save(cabangPelapor);
        LOGGER.info("Successfully save application with id: " + cabangPelaporRequestDTO.getKodeCabang());
    }

    public List<CabangPelaporResponseDTO> getAll() {
        List<CabangPelaporResponseDTO> cabangPelaporResponseDTOs = null;
        List<CabangPelapor> cabangPelapors = cabangPelaporRepository.findAll();

        if (!cabangPelapors.isEmpty()) {
            cabangPelaporResponseDTOs = new ArrayList<>();
            for (CabangPelapor cabangPelapor : cabangPelapors) {
                CabangPelaporResponseDTO cabangPelaporResponseDTO = new CabangPelaporResponseDTO(cabangPelapor);
                cabangPelaporResponseDTOs.add(cabangPelaporResponseDTO);
            }

        }

        return cabangPelaporResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CabangPelaporResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<CabangPelapor> dataTablesOutput = cabangPelaporRepository.findAll(dataTablesInput);

        List<CabangPelaporResponseDTO> cabangPelaporResponseDTOS = new ArrayList<>();
        for (CabangPelapor cabangPelapor : dataTablesOutput.getData()) {
            CabangPelaporResponseDTO cabangPelaporResponseDTO = new CabangPelaporResponseDTO(cabangPelapor);

            cabangPelaporResponseDTOS.add(cabangPelaporResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(cabangPelaporResponseDTOS);

        return result;
    }

    public CabangPelaporResponseDTO findById(String id) {
        CabangPelaporResponseDTO cabangPelaporResponseDTO = null;
        Optional<CabangPelapor> cabangPelaporOpt = cabangPelaporRepository.findById(id);
        if (cabangPelaporOpt.isPresent()) {
            CabangPelapor cabangPelapor = cabangPelaporOpt.get();

            cabangPelaporResponseDTO = new CabangPelaporResponseDTO(cabangPelapor);
        }

        return cabangPelaporResponseDTO;
    }

    public void deleteById(String id) {
        Optional<CabangPelapor> cabangPelaporOpt = cabangPelaporRepository.findById(id);
        if (cabangPelaporOpt.isPresent()) {
            cabangPelaporRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<CabangPelapor> cabangPelapors = cabangPelaporRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.cabangPelaporToExcel(cabangPelapors);
        return in;
    }
}
