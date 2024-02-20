package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.SlikAgunanBaruRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.SlikAgunanBaruResponseDTO;
import id.co.telkomsigma.portalapps.model.SlikAgunanBaru;
import id.co.telkomsigma.portalapps.repository.SlikAgunanBaruRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedSlikAgunanBaru;
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

@Service
public class SlikAgunanBaruService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SlikAgunanBaruService.class);

    private SlikAgunanBaruRepository slikAgunanBaruRepository;

    @Autowired
    public SlikAgunanBaruService(
            SlikAgunanBaruRepository slikAgunanBaruRepository
    ) {
        this.slikAgunanBaruRepository = slikAgunanBaruRepository;
    }

    public void save(SlikAgunanBaruRequestDTO slikAgunanBaruRequestDTO) {
        SlikAgunanBaru slikAgunanBaru = null;
        Optional<SlikAgunanBaru> slikAgunanBaruOpt = slikAgunanBaruRepository.findByEmbedSlikAgunanBaruNoValidAndEmbedSlikAgunanBaruNoAgunanBaru(slikAgunanBaruRequestDTO.getNoValid(), slikAgunanBaruRequestDTO.getNoAgunanBaru());

        if (slikAgunanBaruRequestDTO.isNew()) {
            //do insert
            if (slikAgunanBaruOpt.isPresent()) {
                throw new DuplicateDataException("No Valid & No Agunan Baru you entered already exist!");
            } else {
                slikAgunanBaru = new SlikAgunanBaru(new EmbedSlikAgunanBaru(slikAgunanBaruRequestDTO));
            }
        } else {
            //do update
            slikAgunanBaru = slikAgunanBaruOpt.get();
        }

        SlikAgunanBaru saved = slikAgunanBaruRepository.save(slikAgunanBaru);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedSlikAgunanBaru().getNoValid().concat(saved.getEmbedSlikAgunanBaru().getNoAgunanBaru()));
    }

    public List<SlikAgunanBaruResponseDTO> getAll() {
        List<SlikAgunanBaruResponseDTO> slikAgunanBaruResponseDTOs = null;
        List<SlikAgunanBaru> slikAgunanBarus = slikAgunanBaruRepository.findAll();

        if (!slikAgunanBarus.isEmpty()) {
            slikAgunanBaruResponseDTOs = new ArrayList<>();
            for (SlikAgunanBaru slikAgunanBaru : slikAgunanBarus) {
                SlikAgunanBaruResponseDTO slikAgunanBaruResponseDTO = new SlikAgunanBaruResponseDTO(slikAgunanBaru);
                slikAgunanBaruResponseDTOs.add(slikAgunanBaruResponseDTO);
            }

        }

        return slikAgunanBaruResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<SlikAgunanBaruResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<SlikAgunanBaru> dataTablesOutput = slikAgunanBaruRepository.findAll(dataTablesInput);

        List<SlikAgunanBaruResponseDTO> slikAgunanBaruResponseDTOS = new ArrayList<>();
        for (SlikAgunanBaru slikAgunanBaru : dataTablesOutput.getData()) {
            SlikAgunanBaruResponseDTO slikAgunanBaruResponseDTO = new SlikAgunanBaruResponseDTO(slikAgunanBaru);

            slikAgunanBaruResponseDTOS.add(slikAgunanBaruResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(slikAgunanBaruResponseDTOS);

        return result;
    }

    public SlikAgunanBaruResponseDTO findById(String id, String id2) {
        SlikAgunanBaruResponseDTO slikAgunanBaruResponseDTO = null;
        Optional<SlikAgunanBaru> slikAgunanBaruOpt = slikAgunanBaruRepository.findByEmbedSlikAgunanBaruNoValidAndEmbedSlikAgunanBaruNoAgunanBaru(id, id2);
        if (slikAgunanBaruOpt.isPresent()) {
            SlikAgunanBaru slikAgunanBaru = slikAgunanBaruOpt.get();

            slikAgunanBaruResponseDTO = new SlikAgunanBaruResponseDTO(slikAgunanBaru);
        }

        return slikAgunanBaruResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<SlikAgunanBaru> slikAgunanBaruOpt = slikAgunanBaruRepository.findByEmbedSlikAgunanBaruNoValidAndEmbedSlikAgunanBaruNoAgunanBaru(id, id2);
        if (slikAgunanBaruOpt.isPresent()) {
            slikAgunanBaruRepository.deleteByEmbedSlikAgunanBaruNoValidAndEmbedSlikAgunanBaruNoAgunanBaru(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<SlikAgunanBaru> slikAgunanBarus = slikAgunanBaruRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.slikAgunanBaruToExcel(slikAgunanBarus);
        return in;
    }
}
