package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PihakLawanRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PihakLawanResponseDTO;
import id.co.telkomsigma.portalapps.model.PihakLawan;
import id.co.telkomsigma.portalapps.repository.PihakLawanRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedPihakLawan;
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
public class PihakLawanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PihakLawanService.class);

    private PihakLawanRepository pihakLawanRepository;

    @Autowired
    public PihakLawanService(
            PihakLawanRepository pihakLawanRepository
    ) {
        this.pihakLawanRepository = pihakLawanRepository;
    }

    public void save(PihakLawanRequestDTO pihakLawanRequestDTO) {
        PihakLawan pihakLawan = null;
        Optional<PihakLawan> pihakLawanOpt = pihakLawanRepository.findByEmbedPihakLawanKdCabangAndEmbedPihakLawanIdPihakLawan(pihakLawanRequestDTO.getKdCabang(), pihakLawanRequestDTO.getIdPihakLawan());

        if (pihakLawanRequestDTO.isNew()) {
            //do insert
            if (pihakLawanOpt.isPresent()) {
                throw new DuplicateDataException("KD CABANG & ID PIHAK LAWAN you entered already exist!");
            } else {
                pihakLawan = new PihakLawan(new EmbedPihakLawan(pihakLawanRequestDTO), pihakLawanRequestDTO);
            }
        } else {
            //do update
            pihakLawan = pihakLawanOpt.get();
            pihakLawan.setIdGroup(pihakLawanRequestDTO.getIdGroup());
            pihakLawan.setKota(pihakLawanRequestDTO.getKota());
            pihakLawan.setTahun(pihakLawanRequestDTO.getTahun());
            pihakLawan.setTglRating(pihakLawanRequestDTO.getTglRating());
            pihakLawan.setKdRating(pihakLawanRequestDTO.getKdRating());
            pihakLawan.setKdLembaga(pihakLawanRequestDTO.getKdLembaga());
            pihakLawan.setGolDeb(pihakLawanRequestDTO.getGolDeb());
            pihakLawan.setHubBank(pihakLawanRequestDTO.getHubBank());
            pihakLawan.setJenisKegUsaha(pihakLawanRequestDTO.getJenisKegUsaha());
            pihakLawan.setKodeNegara(pihakLawanRequestDTO.getKodeNegara());
            pihakLawan.setNpwp(pihakLawanRequestDTO.getNpwp());
            pihakLawan.setNamaLengkap(pihakLawanRequestDTO.getNamaLengkap());
            pihakLawan.setJenisKelamin(pihakLawanRequestDTO.getJenisKelamin());
            pihakLawan.setJenisIdentitas(pihakLawanRequestDTO.getJenisIdentitas());
            pihakLawan.setNomorId(pihakLawanRequestDTO.getNomorId());
            pihakLawan.setKodeWn(pihakLawanRequestDTO.getKodeWn());
        }

        PihakLawan saved = pihakLawanRepository.save(pihakLawan);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedPihakLawan().getKdCabang().concat(saved.getEmbedPihakLawan().getIdPihakLawan()));
    }

    public List<PihakLawanResponseDTO> getAll() {
        List<PihakLawanResponseDTO> pihakLawanResponseDTOs = null;
        List<PihakLawan> pihakLawans = pihakLawanRepository.findAll();

        if (!pihakLawans.isEmpty()) {
            pihakLawanResponseDTOs = new ArrayList<>();
            for (PihakLawan pihakLawan : pihakLawans) {
                PihakLawanResponseDTO pihakLawanResponseDTO = new PihakLawanResponseDTO(pihakLawan);
                pihakLawanResponseDTOs.add(pihakLawanResponseDTO);
            }

        }

        return pihakLawanResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PihakLawanResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<PihakLawan> dataTablesOutput = pihakLawanRepository.findAll(dataTablesInput);

        List<PihakLawanResponseDTO> pihakLawanResponseDTOS = new ArrayList<>();
        for (PihakLawan pihakLawan : dataTablesOutput.getData()) {
            PihakLawanResponseDTO pihakLawanResponseDTO = new PihakLawanResponseDTO(pihakLawan);

            pihakLawanResponseDTOS.add(pihakLawanResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(pihakLawanResponseDTOS);

        return result;
    }

    public PihakLawanResponseDTO findById(String id, String id2) {
        PihakLawanResponseDTO pihakLawanResponseDTO = null;
        Optional<PihakLawan> pihakLawanOpt = pihakLawanRepository.findByEmbedPihakLawanKdCabangAndEmbedPihakLawanIdPihakLawan(id, id2);
        if (pihakLawanOpt.isPresent()) {
            PihakLawan pihakLawan = pihakLawanOpt.get();

            pihakLawanResponseDTO = new PihakLawanResponseDTO(pihakLawan);
        }

        return pihakLawanResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<PihakLawan> pihakLawanOpt = pihakLawanRepository.findByEmbedPihakLawanKdCabangAndEmbedPihakLawanIdPihakLawan(id, id2);
        if (pihakLawanOpt.isPresent()) {
            pihakLawanRepository.deleteByEmbedPihakLawanKdCabangAndEmbedPihakLawanIdPihakLawan(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<PihakLawan> pihakLawans = pihakLawanRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.pihakLawanToExcel(pihakLawans);
        return in;
    }
}
