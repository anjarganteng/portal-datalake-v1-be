package id.co.telkomsigma.monitorantasena.service;

import id.co.telkomsigma.monitorantasena.model.ProsesGenerate;
import id.co.telkomsigma.monitorantasena.repository.ProsesGenerateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class ProsesGenerateService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProsesGenerateService.class);

    private ProsesGenerateRepository prosesGenerateRepository;

    @Autowired
    public ProsesGenerateService(
            ProsesGenerateRepository prosesGenerateRepository
    ) {
        this.prosesGenerateRepository = prosesGenerateRepository;
    }

    public void regenerateData(String namaProses) {
        Optional<ProsesGenerate> prosesGenerateOpt = prosesGenerateRepository.findByNamaProsesAndStatusProses(namaProses, "selesai");
        if (prosesGenerateOpt.isPresent()) {
            ProsesGenerate prosesGenerate = prosesGenerateOpt.get();
            // do update
            prosesGenerate.setStatusData("aktif");
            prosesGenerateRepository.save(prosesGenerate);
            LOGGER.info("Successfully regenerate data");
        } else {
            LOGGER.info("Can't regenerate data");
            throw new RuntimeException("Can't regenerate data");
        }
    }
}

