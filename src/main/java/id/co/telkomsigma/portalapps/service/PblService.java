package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PblRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PblResponseDTO;
import id.co.telkomsigma.portalapps.model.Pbl;
import id.co.telkomsigma.portalapps.repository.PblRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedPbl;
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
public class PblService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PblService.class);

    private PblRepository pblRepository;

    @Autowired
    public PblService(
            PblRepository pblRepository
    ) {
        this.pblRepository = pblRepository;
    }

    public void save(PblRequestDTO pblRequestDTO) {
        Pbl pbl = null;
        Optional<Pbl> pblOpt = pblRepository.findByEmbedPblKodeCoaAndEmbedPblMataUangAndEmbedPblMulai(pblRequestDTO.getKodeCoa(), pblRequestDTO.getMataUang(), pblRequestDTO.getMulai());

        if (pblRequestDTO.isNew()) {
            //do insert
            if (pblOpt.isPresent()) {
                throw new DuplicateDataException("Kode Coa & Mata Uang & Mulai you entered already exist!");
            } else {
                pbl = new Pbl(new EmbedPbl(pblRequestDTO), pblRequestDTO);
            }
        } else {
            //do update
            pbl = pblOpt.get();
            pbl.setDeskripsiCoa(pblRequestDTO.getDeskripsiCoa());
            pbl.setDalamLuarNegeri(pblRequestDTO.getDalamLuarNegeri());
            pbl.setJenis(pblRequestDTO.getJenis());
            pbl.setSandiJenis(pblRequestDTO.getSandiJenis());
            pbl.setBranch(pblRequestDTO.getBranch());
            pbl.setWilayah(pblRequestDTO.getWilayah());
            pbl.setHubDgnBank(pblRequestDTO.getHubDgnBank());
            pbl.setSandiBank(pblRequestDTO.getSandiBank());
            pbl.setNoRekening(pblRequestDTO.getNoRekening());
            pbl.setJatuhTempo(pblRequestDTO.getJatuhTempo());
            pbl.setKlasifikasiAset(pblRequestDTO.getKlasifikasiAset());
            pbl.setKualitasAset(pblRequestDTO.getKualitasAset());
            pbl.setPd(pblRequestDTO.getPd());
            pbl.setLgd(pblRequestDTO.getLgd());
            pbl.setSukuBunga(pblRequestDTO.getSukuBunga());
            pbl.setJnsSukuBunga(pblRequestDTO.getJnsSukuBunga());
        }

        Pbl saved = pblRepository.save(pbl);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedPbl().getKodeCoa().concat(saved.getEmbedPbl().getMataUang()));
    }

    public List<PblResponseDTO> getAll() {
        List<PblResponseDTO> pblResponseDTOs = null;
        List<Pbl> pbls = pblRepository.findAll();

        if (!pbls.isEmpty()) {
            pblResponseDTOs = new ArrayList<>();
            for (Pbl pbl : pbls) {
                PblResponseDTO pblResponseDTO = new PblResponseDTO(pbl);
                pblResponseDTOs.add(pblResponseDTO);
            }

        }

        return pblResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PblResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Pbl> dataTablesOutput = pblRepository.findAll(dataTablesInput);

        List<PblResponseDTO> pblResponseDTOS = new ArrayList<>();
        for (Pbl pbl : dataTablesOutput.getData()) {
            PblResponseDTO pblResponseDTO = new PblResponseDTO(pbl);

            pblResponseDTOS.add(pblResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(pblResponseDTOS);

        return result;
    }

    public PblResponseDTO findById(String id, String id2, String id3) {
        PblResponseDTO pblResponseDTO = null;
        Optional<Pbl> pblOpt = pblRepository.findByEmbedPblKodeCoaAndEmbedPblMataUangAndEmbedPblMulai(id, id2, id3);
        if (pblOpt.isPresent()) {
            Pbl pbl = pblOpt.get();

            pblResponseDTO = new PblResponseDTO(pbl);
        }

        return pblResponseDTO;
    }

    public void deleteById(String id, String id2, String id3) {
        Optional<Pbl> pblOpt = pblRepository.findByEmbedPblKodeCoaAndEmbedPblMataUangAndEmbedPblMulai(id, id2, id3);
        if (pblOpt.isPresent()) {
            pblRepository.deleteByEmbedPblKodeCoaAndEmbedPblMataUangAndEmbedPblMulai(id, id2, id3);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Pbl> pbls = pblRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.pblToExcel(pbls);
        return in;
    }
}
