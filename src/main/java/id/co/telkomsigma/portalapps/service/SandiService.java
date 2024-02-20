package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.SandiResponseDTO;
import id.co.telkomsigma.portalapps.model.Sandi;
import id.co.telkomsigma.portalapps.repository.SandiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class SandiService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SandiService.class);

    private SandiRepository sandiRepository;

    @Autowired
    public SandiService(
            SandiRepository sandiRepository
    ) {
        this.sandiRepository = sandiRepository;
    }

    public boolean getSandi(String sandi) {
        Optional<Sandi> dataOpt = sandiRepository.findById(sandi);
        return dataOpt.isPresent();
    }

    public List<SandiResponseDTO> getAll(String tipeSandi) {
        List<SandiResponseDTO> sandiResponseDTOs = null;
        List<Sandi> sandis = sandiRepository.getAllByTipeSandi(tipeSandi);

        if (!sandis.isEmpty()) {
            sandiResponseDTOs = new ArrayList<>();
            for (Sandi sandi : sandis) {
                SandiResponseDTO sandiResponseDTO = new SandiResponseDTO(sandi);
                sandiResponseDTOs.add(sandiResponseDTO);
            }

        }

        return sandiResponseDTOs;
    }
}
