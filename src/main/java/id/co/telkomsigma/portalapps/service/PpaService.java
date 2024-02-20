package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.PpaRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.PpaResponseDTO;
import id.co.telkomsigma.portalapps.model.Ppa;
import id.co.telkomsigma.portalapps.repository.PpaRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedPpa;
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
public class PpaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PpaService.class);

    private PpaRepository ppaRepository;

    @Autowired
    public PpaService(
            PpaRepository ppaRepository
    ) {
        this.ppaRepository = ppaRepository;
    }

    public void save(PpaRequestDTO ppaRequestDTO) {
        Ppa ppa = null;
        Optional<Ppa> ppaOpt = ppaRepository.findByEmbedPpaSandiJenisAndEmbedPpaCabangAndEmbedPpaWilayahAndEmbedPpaMataUangAndEmbedPpaTglMulai(ppaRequestDTO.getSandiJenis(), ppaRequestDTO.getCabang(), ppaRequestDTO.getWilayah(), ppaRequestDTO.getMataUang(), ppaRequestDTO.getTglMulai());

        if (ppaRequestDTO.isNew()) {
            //do insert
            if (ppaOpt.isPresent()) {
                throw new DuplicateDataException("Sandi Jenis, Cabang, Wilayah, Mata Uang, Tanggal Mulai you entered already exist!");
            } else {
                ppa = new Ppa(new EmbedPpa(ppaRequestDTO), ppaRequestDTO);
            }
        } else {
            //do update
            ppa = ppaOpt.get();
            ppa.setDeskripsiCoa(ppaRequestDTO.getDeskripsiCoa());
            ppa.setJenis(ppaRequestDTO.getJenis());
            ppa.setKualitasAset(ppaRequestDTO.getKualitasAset());
            ppa.setKodeCoa(ppaRequestDTO.getKodeCoa());
            ppa.setTglJthTempo(ppaRequestDTO.getTglJthTempo());
            ppa.setNilaiBuku(ppaRequestDTO.getNilaiBuku());
            ppa.setAkumulasiSusut(ppaRequestDTO.getAkumulasiSusut());
            ppa.setHargaPerolehan(ppaRequestDTO.getHargaPerolehan());
            ppa.setMetodeUkur(ppaRequestDTO.getMetodeUkur());
            ppa.setNomorAset(ppaRequestDTO.getNomorAset());
            ppa.setStatusAset(ppaRequestDTO.getStatusAset());
        }

        Ppa saved = ppaRepository.save(ppa);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedPpa().getSandiJenis().concat(saved.getEmbedPpa().getTglMulai()));
    }

    public List<PpaResponseDTO> getAll() {
        List<PpaResponseDTO> ppaResponseDTOs = null;
        List<Ppa> ppas = ppaRepository.findAll();

        if (!ppas.isEmpty()) {
            ppaResponseDTOs = new ArrayList<>();
            for (Ppa ppa : ppas) {
                PpaResponseDTO ppaResponseDTO = new PpaResponseDTO(ppa);
                ppaResponseDTOs.add(ppaResponseDTO);
            }

        }

        return ppaResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<PpaResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Ppa> dataTablesOutput = ppaRepository.findAll(dataTablesInput);

        List<PpaResponseDTO> ppaResponseDTOS = new ArrayList<>();
        for (Ppa ppa : dataTablesOutput.getData()) {
            PpaResponseDTO ppaResponseDTO = new PpaResponseDTO(ppa);

            ppaResponseDTOS.add(ppaResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(ppaResponseDTOS);

        return result;
    }

    public PpaResponseDTO findById(String id, String id2, String id3, String id4, String id5) {
        PpaResponseDTO ppaResponseDTO = null;
        Optional<Ppa> ppaOpt = ppaRepository.findByEmbedPpaSandiJenisAndEmbedPpaCabangAndEmbedPpaWilayahAndEmbedPpaMataUangAndEmbedPpaTglMulai(id, id2, id3, id4, id5);
        if (ppaOpt.isPresent()) {
            Ppa ppa = ppaOpt.get();

            ppaResponseDTO = new PpaResponseDTO(ppa);
        }

        return ppaResponseDTO;
    }

    public void deleteById(String id, String id2, String id3, String id4, String id5) {
        Optional<Ppa> ppaOpt = ppaRepository.findByEmbedPpaSandiJenisAndEmbedPpaCabangAndEmbedPpaWilayahAndEmbedPpaMataUangAndEmbedPpaTglMulai(id, id2, id3, id4, id5);
        if (ppaOpt.isPresent()) {
            ppaRepository.deleteByEmbedPpaSandiJenisAndEmbedPpaCabangAndEmbedPpaWilayahAndEmbedPpaMataUangAndEmbedPpaTglMulai(id, id2, id3, id4, id5);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Ppa> ppas = ppaRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.ppaToExcel(ppas);
        return in;
    }
}

