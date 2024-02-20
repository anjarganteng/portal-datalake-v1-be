package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CifjoinRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CifjoinResponseDTO;
import id.co.telkomsigma.portalapps.model.Cifjoin;
import id.co.telkomsigma.portalapps.repository.CifjoinRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedCifjoin;
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
public class CifjoinService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CifjoinService.class);

    private CifjoinRepository cifjoinRepository;

    @Autowired
    public CifjoinService(
            CifjoinRepository cifjoinRepository
    ) {
        this.cifjoinRepository = cifjoinRepository;
    }

    public void save(CifjoinRequestDTO cifjoinRequestDTO) {
        Cifjoin cifjoin = null;
        Optional<Cifjoin> cifjoinOpt = cifjoinRepository.findByEmbedCifjoinCifAndEmbedCifjoinCifJoin(cifjoinRequestDTO.getCif(), cifjoinRequestDTO.getCifJoin());

        if (cifjoinRequestDTO.isNew()) {
            // do insert
            if (cifjoinOpt.isPresent()) {
                throw new DuplicateDataException("Cif & Cif Join you entered already exist!");
            } else {

                Optional<Cifjoin> da = cifjoinRepository.findByEmbedCifjoinCifJoin(cifjoinRequestDTO.getCifJoin());

                if (da.isPresent()) {
                    if (!da.get().getTipeJoin().equals(cifjoinRequestDTO.getTipeJoin())) {
                        String sf = String.format("CIF JOIN: %s must set to TIPE JOIN: %s",
                                cifjoinRequestDTO.getCifJoin(), da.get().getTipeJoin());
                        throw new RuntimeException(sf);
                    }
                } else {
                    ;
                }

                cifjoin = new Cifjoin(new EmbedCifjoin(cifjoinRequestDTO), cifjoinRequestDTO);
            }
        } else {
            // do update
            cifjoin = cifjoinOpt.get();
            cifjoin.setFlagNasabah(cifjoinRequestDTO.getFlagNasabah());
            cifjoin.setNoIdentitas(cifjoinRequestDTO.getNoIdentitas());
            cifjoin.setTipeNasabah(cifjoinRequestDTO.getTipeNasabah());
            cifjoin.setTipeJoin(cifjoinRequestDTO.getTipeJoin());
        }

        cifjoinRepository.save(cifjoin);
        LOGGER.info("Successfully save application with id: " + cifjoinRequestDTO.getCif());
    }

    public List<CifjoinResponseDTO> getAll() {
        List<CifjoinResponseDTO> cifjoinResponseDTOs = null;
        List<Cifjoin> cifjoins = cifjoinRepository.findAll();

        if (!cifjoins.isEmpty()) {
            cifjoinResponseDTOs = new ArrayList<>();
            for (Cifjoin cifjoin : cifjoins) {
                CifjoinResponseDTO cifjoinResponseDTO = new CifjoinResponseDTO(cifjoin);
                cifjoinResponseDTOs.add(cifjoinResponseDTO);
            }

        }

        return cifjoinResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CifjoinResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Cifjoin> dataTablesOutput = cifjoinRepository.findAll(dataTablesInput);

        List<CifjoinResponseDTO> cifjoinResponseDTOS = new ArrayList<>();
        for (Cifjoin cifjoin : dataTablesOutput.getData()) {
            CifjoinResponseDTO cifjoinResponseDTO = new CifjoinResponseDTO(cifjoin);

            cifjoinResponseDTOS.add(cifjoinResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(cifjoinResponseDTOS);

        return result;
    }

    public CifjoinResponseDTO findById(String id, String id2) {
        CifjoinResponseDTO cifjoinResponseDTO = null;
        Optional<Cifjoin> cifjoinOpt = cifjoinRepository.findByEmbedCifjoinCifAndEmbedCifjoinCifJoin(id, id2);
        if (cifjoinOpt.isPresent()) {
            Cifjoin cifjoin = cifjoinOpt.get();

            cifjoinResponseDTO = new CifjoinResponseDTO(cifjoin);
        }

        return cifjoinResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<Cifjoin> cifjoinOpt = cifjoinRepository.findByEmbedCifjoinCifAndEmbedCifjoinCifJoin(id, id2);
        if (cifjoinOpt.isPresent()) {
            cifjoinRepository.deleteByEmbedCifjoinCifAndEmbedCifjoinCifJoin(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Cifjoin> cifjoins = cifjoinRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.cifjoinToExcel(cifjoins);
        return in;
    }
}

