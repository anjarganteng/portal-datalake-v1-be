package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.KursRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.KursResponseDTO;
import id.co.telkomsigma.portalapps.model.Kurs;
import id.co.telkomsigma.portalapps.repository.KursRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedKurs;
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
public class KursService {

    public static final Logger LOGGER = LoggerFactory.getLogger(KursService.class);

    private KursRepository kursRepository;

    @Autowired
    public KursService(
            KursRepository kursRepository
    ) {
        this.kursRepository = kursRepository;
    }

    public void save(KursRequestDTO kursRequestDTO) {
        Kurs kurs = null;
        Optional<Kurs> kursOpt = kursRepository.findByEmbedKursMataUangAndEmbedKursTanggalAndEmbedKursJenis(kursRequestDTO.getMataUang(), kursRequestDTO.getTanggal(), kursRequestDTO.getJenis());

        if (kursRequestDTO.isNew()) {
            //do insert
            if (kursOpt.isPresent()) {
                throw new DuplicateDataException("Mata Uang & Tanggal you entered already exist!");
            } else {
                kurs = new Kurs(new EmbedKurs(kursRequestDTO), kursRequestDTO);
            }
        } else {
            //do update
            kurs = kursOpt.get();
            kurs.setKursJual(kursRequestDTO.getKursJual());
            kurs.setKursBeli(kursRequestDTO.getKursBeli());
        }

        Kurs saved = kursRepository.save(kurs);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedKurs().getMataUang().concat(saved.getEmbedKurs().getTanggal()).concat(saved.getEmbedKurs().getJenis()));
    }

    public List<KursResponseDTO> getAll() {
        List<KursResponseDTO> kursResponseDTOs = null;
        List<Kurs> kurss = kursRepository.findAll();

        if (!kurss.isEmpty()) {
            kursResponseDTOs = new ArrayList<>();
            for (Kurs kurs : kurss) {
                KursResponseDTO kursResponseDTO = new KursResponseDTO(kurs);
                kursResponseDTOs.add(kursResponseDTO);
            }

        }

        return kursResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<KursResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Kurs> dataTablesOutput = kursRepository.findAll(dataTablesInput);

        List<KursResponseDTO> kursResponseDTOS = new ArrayList<>();
        for (Kurs kurs : dataTablesOutput.getData()) {
            KursResponseDTO kursResponseDTO = new KursResponseDTO(kurs);

            kursResponseDTOS.add(kursResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(kursResponseDTOS);

        return result;
    }

    public KursResponseDTO findById(String id, String id2, String id3) {
        KursResponseDTO kursResponseDTO = null;
        Optional<Kurs> kursOptional = kursRepository.findByEmbedKursMataUangAndEmbedKursTanggalAndEmbedKursJenis(id, id2, id3);
        if (kursOptional.isPresent()) {
            Kurs kurs = kursOptional.get();

            kursResponseDTO = new KursResponseDTO(kurs);
        }

        return kursResponseDTO;
    }

    public void deleteById(String id, String id2, String id3) {
        Optional<Kurs> kursOpt = kursRepository.findByEmbedKursMataUangAndEmbedKursTanggalAndEmbedKursJenis(id, id2, id3);
        if (kursOpt.isPresent()) {
            kursRepository.deleteByEmbedKursMataUangAndEmbedKursTanggalAndEmbedKursJenis(id, id2, id3);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2).concat(id3));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2).concat(id3));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Kurs> kurss = kursRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.kursToExcel(kurss);
        return in;
    }
}
