package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PbiRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PbiResponseDTO;
import id.co.telkomsigma.portalapps.model.Pbi;
import id.co.telkomsigma.portalapps.repository.PbiRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedPbi;
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
public class PbiService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PbiService.class);

    private PbiRepository pbiRepository;

    @Autowired
    public PbiService(
            PbiRepository pbiRepository
    ) {
        this.pbiRepository = pbiRepository;
    }

    public void save(PbiRequestDTO pbiRequestDTO) {
        Pbi pbi = null;
        Optional<Pbi> pbiOpt = pbiRepository.findByEmbedPbiKodeCoaAndEmbedPbiMataUangAndEmbedPbiMulai(pbiRequestDTO.getKodeCoa(), pbiRequestDTO.getMataUang(), pbiRequestDTO.getMulai());

        if (pbiRequestDTO.isNew()) {
            //do insert
            if (pbiOpt.isPresent()) {
                throw new DuplicateDataException("Kode Coa & Mata Uang & Mulai you entered already exist!");
            } else {
                pbi = new Pbi(new EmbedPbi(pbiRequestDTO), pbiRequestDTO);
            }
        } else {
            //do update
            pbi = pbiOpt.get();
            pbi.setDeskripsiCoa(pbiRequestDTO.getDeskripsiCoa());
            pbi.setDalamLuarNegeri(pbiRequestDTO.getDalamLuarNegeri());
            pbi.setJenis(pbiRequestDTO.getJenis());
            pbi.setSandiJenis(pbiRequestDTO.getSandiJenis());
            pbi.setBranch(pbiRequestDTO.getBranch());
            pbi.setWilayah(pbiRequestDTO.getWilayah());
            pbi.setHubDgnBank(pbiRequestDTO.getHubDgnBank());
            pbi.setSandiBank(pbiRequestDTO.getSandiBank());
            pbi.setNoRekening(pbiRequestDTO.getNoRekening());
            pbi.setJatuhTempo(pbiRequestDTO.getJatuhTempo());
            pbi.setKlasifikasiAset(pbiRequestDTO.getKlasifikasiAset());
            pbi.setKualitasAset(pbiRequestDTO.getKualitasAset());
            pbi.setSukuBunga(pbiRequestDTO.getSukuBunga());
        }

        Pbi saved = pbiRepository.save(pbi);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedPbi().getKodeCoa().concat(saved.getEmbedPbi().getMataUang()));
    }

    public List<PbiResponseDTO> getAll() {
        List<PbiResponseDTO> pbiResponseDTOs = null;
        List<Pbi> pbis = pbiRepository.findAll();

        if (!pbis.isEmpty()) {
            pbiResponseDTOs = new ArrayList<>();
            for (Pbi pbi : pbis) {
                PbiResponseDTO pbiResponseDTO = new PbiResponseDTO(pbi);
                pbiResponseDTOs.add(pbiResponseDTO);
            }

        }

        return pbiResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PbiResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Pbi> dataTablesOutput = pbiRepository.findAll(dataTablesInput);

        List<PbiResponseDTO> pbiResponseDTOS = new ArrayList<>();
        for (Pbi pbi : dataTablesOutput.getData()) {
            PbiResponseDTO pbiResponseDTO = new PbiResponseDTO(pbi);

            pbiResponseDTOS.add(pbiResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(pbiResponseDTOS);

        return result;
    }

    public PbiResponseDTO findById(String id, String id2, String id3) {
        PbiResponseDTO pbiResponseDTO = null;
        Optional<Pbi> pbiOpt = pbiRepository.findByEmbedPbiKodeCoaAndEmbedPbiMataUangAndEmbedPbiMulai(id, id2, id3);
        if (pbiOpt.isPresent()) {
            Pbi pbi = pbiOpt.get();

            pbiResponseDTO = new PbiResponseDTO(pbi);
        }

        return pbiResponseDTO;
    }

    public void deleteById(String id, String id2, String id3) {
        Optional<Pbi> pbiOpt = pbiRepository.findByEmbedPbiKodeCoaAndEmbedPbiMataUangAndEmbedPbiMulai(id, id2, id3);
        if (pbiOpt.isPresent()) {
            pbiRepository.deleteByEmbedPbiKodeCoaAndEmbedPbiMataUangAndEmbedPbiMulai(id, id2, id3);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Pbi> pbis = pbiRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.pbiToExcel(pbis);
        return in;
    }
}
