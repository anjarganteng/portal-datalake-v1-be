package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.NasabahjoinRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.NasabahjoinResponseDTO;
import id.co.telkomsigma.portalapps.model.Nasabahjoin;
import id.co.telkomsigma.portalapps.repository.NasabahjoinRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class NasabahjoinService {

    public static final Logger LOGGER = LoggerFactory.getLogger(NasabahjoinService.class);

    private NasabahjoinRepository nasabahjoinRepository;

    @Autowired
    public NasabahjoinService(
            NasabahjoinRepository nasabahjoinRepository
    ) {
        this.nasabahjoinRepository = nasabahjoinRepository;
    }

    public void save(NasabahjoinRequestDTO nasabahjoinRequestDTO) {
        Nasabahjoin nasabahjoin = null;
        Optional<Nasabahjoin> nasabahjoinOpt = nasabahjoinRepository.findById(nasabahjoinRequestDTO.getCif());

        if (nasabahjoinRequestDTO.isNew()) {
            //do insert
            if (nasabahjoinOpt.isPresent()) {
                throw new DuplicateDataException("CIF you entered already exist!");
            } else {
                nasabahjoin = new Nasabahjoin(nasabahjoinRequestDTO);
            }
        } else {
            //do update
            nasabahjoin = nasabahjoinOpt.get();
            nasabahjoin.setAlamat(nasabahjoinRequestDTO.getAlamat());
            nasabahjoin.setFlagNasabah(nasabahjoinRequestDTO.getFlagNasabah());
            nasabahjoin.setCifJoin(nasabahjoinRequestDTO.getCifJoin());
            nasabahjoin.setFlagFraud(nasabahjoinRequestDTO.getFlagFraud());
            nasabahjoin.setFlagJoin(nasabahjoinRequestDTO.getFlagJoin());
            nasabahjoin.setGolNsb(nasabahjoinRequestDTO.getGolNsb());
            nasabahjoin.setJnsIdentitas(nasabahjoinRequestDTO.getJnsIdentitas());
            nasabahjoin.setJnsIdentitasPemegangKuasa(nasabahjoinRequestDTO.getJnsIdentitasPemegangKuasa());
            nasabahjoin.setKabKota(nasabahjoinRequestDTO.getKabKota());
            nasabahjoin.setKategoriUsaha(nasabahjoinRequestDTO.getKategoriUsaha());
            nasabahjoin.setKewarnegaraan(nasabahjoinRequestDTO.getKewarnegaraan());
            nasabahjoin.setNoTelp(nasabahjoinRequestDTO.getNoTelp());
            nasabahjoin.setTglLahir(nasabahjoinRequestDTO.getTglLahir());
            nasabahjoin.setNoNpwp(nasabahjoinRequestDTO.getNoNpwp());
            nasabahjoin.setTmpLahir(nasabahjoinRequestDTO.getTmpLahir());
            nasabahjoin.setNmIbuKdg(nasabahjoinRequestDTO.getNmIbuKdg());
            nasabahjoin.setNmLnkpNsb(nasabahjoinRequestDTO.getNmLnkpNsb());
            nasabahjoin.setNmLnkpPemegangKuasa(nasabahjoinRequestDTO.getNmLnkpPemegangKuasa());
            nasabahjoin.setNoSiup(nasabahjoinRequestDTO.getNoSiup());
            nasabahjoin.setNoIdentitas(nasabahjoinRequestDTO.getNoIdentitas());
            nasabahjoin.setNoIdentitasPemegangKuasa(nasabahjoinRequestDTO.getNoIdentitasPemegangKuasa());
            nasabahjoin.setHubDgnBank(nasabahjoinRequestDTO.getHubDgnBank());
            nasabahjoin.setTipeCif(nasabahjoinRequestDTO.getTipeCif());
        }

        Nasabahjoin saved = nasabahjoinRepository.save(nasabahjoin);
        LOGGER.info("Successfully save application with id: " + saved.getCif());
    }

    public List<NasabahjoinResponseDTO> getAll() {
        List<NasabahjoinResponseDTO> nasabahjoinResponseDTOs = null;
        List<Nasabahjoin> nasabahjoins = nasabahjoinRepository.findAll();

        if (!nasabahjoins.isEmpty()) {
            nasabahjoinResponseDTOs = new ArrayList<>();
            for (Nasabahjoin nasabahjoin : nasabahjoins) {
                NasabahjoinResponseDTO nasabahjoinResponseDTO = new NasabahjoinResponseDTO(nasabahjoin);
                nasabahjoinResponseDTOs.add(nasabahjoinResponseDTO);
            }

        }

        return nasabahjoinResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        Specification<Nasabahjoin> formSpecification = (Specification<Nasabahjoin>) (root, query, criteriaBuilder) -> {
            Expression<String> formExpression = criteriaBuilder.lower(root.get("flagNasabah"));
            return criteriaBuilder.equal(formExpression, "0");
        };
        return nasabahjoinRepository.findAll(dataTablesInput, formSpecification);
    }

    public NasabahjoinResponseDTO findById(String id) {
        NasabahjoinResponseDTO nasabahjoinResponseDTO = null;
        Optional<Nasabahjoin> nasabahjoinOpt = nasabahjoinRepository.findById(id);
        if (nasabahjoinOpt.isPresent()) {
            Nasabahjoin nasabahjoin = nasabahjoinOpt.get();

            nasabahjoinResponseDTO = new NasabahjoinResponseDTO(nasabahjoin);
        }

        return nasabahjoinResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Nasabahjoin> nasabahjoinOpt = nasabahjoinRepository.findById(id);
        if (nasabahjoinOpt.isPresent()) {
            nasabahjoinRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Nasabahjoin> nasabahjoins = nasabahjoinRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.nasabahjoinToExcel(nasabahjoins);
        return in;
    }
}
