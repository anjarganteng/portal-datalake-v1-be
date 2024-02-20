package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.SlikAgunanLamaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.SlikAgunanLamaResponseDTO;
import id.co.telkomsigma.portalapps.model.SlikAgunanLama;
import id.co.telkomsigma.portalapps.repository.SlikAgunanLamaRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedSlikAgunanLama;
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
public class SlikAgunanLamaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SlikAgunanLamaService.class);

    private SlikAgunanLamaRepository slikAgunanLamaRepository;

    @Autowired
    public SlikAgunanLamaService(
            SlikAgunanLamaRepository slikAgunanLamaRepository
    ) {
        this.slikAgunanLamaRepository = slikAgunanLamaRepository;
    }

    public void save(SlikAgunanLamaRequestDTO slikAgunanLamaRequestDTO) {
        SlikAgunanLama slikAgunanLama = null;
        Optional<SlikAgunanLama> slikAgunanLamaOpt = slikAgunanLamaRepository.findByEmbedSlikAgunanLamaNoSlikLamaAndEmbedSlikAgunanLamaNoAgunanLama(slikAgunanLamaRequestDTO.getNoSlikLama(), slikAgunanLamaRequestDTO.getNoAgunanLama());

        if (slikAgunanLamaRequestDTO.isNew()) {
            //do insert
            if (slikAgunanLamaOpt.isPresent()) {
                throw new DuplicateDataException("No Slik Lama & No Agunan Lama you entered already exist!");
            } else {
                slikAgunanLama = new SlikAgunanLama(new EmbedSlikAgunanLama(slikAgunanLamaRequestDTO));
            }
        } else {
            //do update
            slikAgunanLama = slikAgunanLamaOpt.get();
        }

        SlikAgunanLama saved = slikAgunanLamaRepository.save(slikAgunanLama);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedSlikAgunanLama().getNoSlikLama().concat(saved.getEmbedSlikAgunanLama().getNoAgunanLama()));
    }

    public List<SlikAgunanLamaResponseDTO> getAll() {
        List<SlikAgunanLamaResponseDTO> slikAgunanLamaResponseDTOs = null;
        List<SlikAgunanLama> slikAgunanLamas = slikAgunanLamaRepository.findAll();

        if (!slikAgunanLamas.isEmpty()) {
            slikAgunanLamaResponseDTOs = new ArrayList<>();
            for (SlikAgunanLama slikAgunanLama : slikAgunanLamas) {
                SlikAgunanLamaResponseDTO slikAgunanLamaResponseDTO = new SlikAgunanLamaResponseDTO(slikAgunanLama);
                slikAgunanLamaResponseDTOs.add(slikAgunanLamaResponseDTO);
            }

        }

        return slikAgunanLamaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<SlikAgunanLamaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<SlikAgunanLama> dataTablesOutput = slikAgunanLamaRepository.findAll(dataTablesInput);

        List<SlikAgunanLamaResponseDTO> slikAgunanLamaResponseDTOS = new ArrayList<>();
        for (SlikAgunanLama slikAgunanLama : dataTablesOutput.getData()) {
            SlikAgunanLamaResponseDTO slikAgunanLamaResponseDTO = new SlikAgunanLamaResponseDTO(slikAgunanLama);

            slikAgunanLamaResponseDTOS.add(slikAgunanLamaResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(slikAgunanLamaResponseDTOS);

        return result;
    }

    public SlikAgunanLamaResponseDTO findById(String id, String id2) {
        SlikAgunanLamaResponseDTO slikAgunanLamaResponseDTO = null;
        Optional<SlikAgunanLama> slikAgunanLamaOpt = slikAgunanLamaRepository.findByEmbedSlikAgunanLamaNoSlikLamaAndEmbedSlikAgunanLamaNoAgunanLama(id, id2);
        if (slikAgunanLamaOpt.isPresent()) {
            SlikAgunanLama slikAgunanLama = slikAgunanLamaOpt.get();

            slikAgunanLamaResponseDTO = new SlikAgunanLamaResponseDTO(slikAgunanLama);
        }

        return slikAgunanLamaResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<SlikAgunanLama> slikAgunanLamaOpt = slikAgunanLamaRepository.findByEmbedSlikAgunanLamaNoSlikLamaAndEmbedSlikAgunanLamaNoAgunanLama(id, id2);
        if (slikAgunanLamaOpt.isPresent()) {
            slikAgunanLamaRepository.deleteByEmbedSlikAgunanLamaNoSlikLamaAndEmbedSlikAgunanLamaNoAgunanLama(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<SlikAgunanLama> slikAgunanLamas = slikAgunanLamaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.slikAgunanLamaToExcel(slikAgunanLamas);
        return in;
    }
}
