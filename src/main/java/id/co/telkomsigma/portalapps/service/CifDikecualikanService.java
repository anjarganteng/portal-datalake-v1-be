package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CifDikecualikanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CifDikecualikanResponseDTO;
import id.co.telkomsigma.portalapps.model.CifDikecualikan;
import id.co.telkomsigma.portalapps.repository.CifDikecualikanRepository;
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
public class CifDikecualikanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CifDikecualikanService.class);

    private CifDikecualikanRepository cifDikecualikanRepository;

    @Autowired
    public CifDikecualikanService(
            CifDikecualikanRepository cifDikecualikanRepository
    ) {
        this.cifDikecualikanRepository = cifDikecualikanRepository;
    }

    public void save(CifDikecualikanRequestDTO cifDikecualikanRequestDTO) {
        CifDikecualikan cifDikecualikan = null;
        Optional<CifDikecualikan> cifDikecualikanOpt = cifDikecualikanRepository.findById(cifDikecualikanRequestDTO.getNoCif());

        if (cifDikecualikanRequestDTO.isNew()) {
            //do insert
            if (cifDikecualikanOpt.isPresent()) {
                throw new DuplicateDataException("No Cif you entered already exist!");
            } else {
                cifDikecualikan = new CifDikecualikan(cifDikecualikanRequestDTO);
            }
        } else {
            //do update
            cifDikecualikan = cifDikecualikanOpt.get();
            cifDikecualikan.setFlag(cifDikecualikanRequestDTO.getFlag());
            cifDikecualikan.setKeterangan(cifDikecualikanRequestDTO.getKeterangan());
            cifDikecualikan.setFlagDnLn(cifDikecualikanRequestDTO.getFlagDnLn());
        }

        CifDikecualikan saved = cifDikecualikanRepository.save(cifDikecualikan);
        LOGGER.info("Successfully save application with id: " + saved.getNoCif());
    }

    public List<CifDikecualikanResponseDTO> getAll() {
        List<CifDikecualikanResponseDTO> cifDikecualikanResponseDTOs = null;
        List<CifDikecualikan> cifDikecualikans = cifDikecualikanRepository.findAll();

        if (!cifDikecualikans.isEmpty()) {
            cifDikecualikanResponseDTOs = new ArrayList<>();
            for (CifDikecualikan cifDikecualikan : cifDikecualikans) {
                CifDikecualikanResponseDTO cifDikecualikanResponseDTO = new CifDikecualikanResponseDTO(cifDikecualikan);
                cifDikecualikanResponseDTOs.add(cifDikecualikanResponseDTO);
            }

        }

        return cifDikecualikanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CifDikecualikanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<CifDikecualikan> dataTablesOutput = cifDikecualikanRepository.findAll(dataTablesInput);

        List<CifDikecualikanResponseDTO> cifDikecualikanResponseDTOS = new ArrayList<>();
        for (CifDikecualikan cifDikecualikan : dataTablesOutput.getData()) {
            CifDikecualikanResponseDTO cifDikecualikanResponseDTO = new CifDikecualikanResponseDTO(cifDikecualikan);

            cifDikecualikanResponseDTOS.add(cifDikecualikanResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(cifDikecualikanResponseDTOS);

        return result;
    }

    public CifDikecualikanResponseDTO findById(String id) {
        CifDikecualikanResponseDTO cifDikecualikanResponseDTO = null;
        Optional<CifDikecualikan> cifDikecualikanOpt = cifDikecualikanRepository.findById(id);
        if (cifDikecualikanOpt.isPresent()) {
            CifDikecualikan cifDikecualikan = cifDikecualikanOpt.get();

            cifDikecualikanResponseDTO = new CifDikecualikanResponseDTO(cifDikecualikan);
        }

        return cifDikecualikanResponseDTO;
    }

    public void deleteById(String id) {
        Optional<CifDikecualikan> cifDikecualikanOpt = cifDikecualikanRepository.findById(id);
        if (cifDikecualikanOpt.isPresent()) {
            cifDikecualikanRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<CifDikecualikan> cifDikecualikans = cifDikecualikanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.cifDikecualikanToExcel(cifDikecualikans);
        return in;
    }
}
