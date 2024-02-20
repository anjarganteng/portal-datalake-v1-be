package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.AtbRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.AtbResponseDTO;
import id.co.telkomsigma.portalapps.model.Atb;
import id.co.telkomsigma.portalapps.repository.AtbRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedAtb;
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
public class AtbService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AtbService.class);

    private AtbRepository atbRepository;

    @Autowired
    public AtbService(
            AtbRepository atbRepository
    ) {
        this.atbRepository = atbRepository;
    }

    public void save(AtbRequestDTO atbRequestDTO) {
        Atb atb = null;
        Optional<Atb> atbOpt = atbRepository.findByEmbedAtbJenisAsetAndEmbedAtbTglPerolehanAndEmbedAtbTglMulai(atbRequestDTO.getJenisAset(), atbRequestDTO.getTglPerolehan(), atbRequestDTO.getTglMulai());

        if (atbRequestDTO.isNew()) {
            //do insert
            if (atbOpt.isPresent()) {
                throw new DuplicateDataException("Jenis Aset & Tgl Perolehan & Tgl Mulai you entered already exist!");
            } else {
                atb = new Atb(new EmbedAtb(atbRequestDTO), atbRequestDTO);
            }
        } else {
            //do update
            atb = atbOpt.get();
            atb.setJthTempo(atbRequestDTO.getJthTempo());
            atb.setJenisValuta(atbRequestDTO.getJenisValuta());
            atb.setMetodeUkur(atbRequestDTO.getMetodeUkur());
            atb.setJmlBlnLalu(atbRequestDTO.getJmlBlnLalu());
            atb.setJmlDebet(atbRequestDTO.getJmlDebet());
            atb.setJmlKredit(atbRequestDTO.getJmlKredit());
            atb.setJmlLainnya(atbRequestDTO.getJmlLainnya());
            atb.setJmlBlnLaporan(atbRequestDTO.getJmlBlnLaporan());
            atb.setAkumulasiAmortasi(atbRequestDTO.getAkumulasiAmortasi());
        }

        Atb saved = atbRepository.save(atb);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedAtb().getJenisAset().concat(saved.getEmbedAtb().getTglPerolehan()));
    }

    public List<AtbResponseDTO> getAll() {
        List<AtbResponseDTO> atbResponseDTOs = null;
        List<Atb> atbs = atbRepository.findAll();

        if (!atbs.isEmpty()) {
            atbResponseDTOs = new ArrayList<>();
            for (Atb atb : atbs) {
                AtbResponseDTO atbResponseDTO = new AtbResponseDTO(atb);
                atbResponseDTOs.add(atbResponseDTO);
            }

        }

        return atbResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<AtbResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Atb> dataTablesOutput = atbRepository.findAll(dataTablesInput);

        List<AtbResponseDTO> atbResponseDTOS = new ArrayList<>();
        for (Atb atb : dataTablesOutput.getData()) {
            AtbResponseDTO atbResponseDTO = new AtbResponseDTO(atb);

            atbResponseDTOS.add(atbResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(atbResponseDTOS);

        return result;
    }

    public AtbResponseDTO findById(String id, String id2, String id3) {
        AtbResponseDTO atbResponseDTO = null;
        Optional<Atb> atbOpt = atbRepository.findByEmbedAtbJenisAsetAndEmbedAtbTglPerolehanAndEmbedAtbTglMulai(id, id2, id3);
        if (atbOpt.isPresent()) {
            Atb atb = atbOpt.get();

            atbResponseDTO = new AtbResponseDTO(atb);
        }

        return atbResponseDTO;
    }

    public void deleteById(String id, String id2, String id3) {
        Optional<Atb> atbOpt = atbRepository.findByEmbedAtbJenisAsetAndEmbedAtbTglPerolehanAndEmbedAtbTglMulai(id, id2, id3);
        if (atbOpt.isPresent()) {
            atbRepository.deleteByEmbedAtbJenisAsetAndEmbedAtbTglPerolehanAndEmbedAtbTglMulai(id, id2, id3);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Atb> atbs = atbRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.atbToExcel(atbs);
        return in;
    }
}
