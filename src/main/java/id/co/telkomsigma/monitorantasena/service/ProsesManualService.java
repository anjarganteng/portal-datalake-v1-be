package id.co.telkomsigma.monitorantasena.service;

import id.co.telkomsigma.monitorantasena.dto.ProsesManualRequestDTO;
import id.co.telkomsigma.monitorantasena.dto.ProsesManualResponseDTO;
import id.co.telkomsigma.monitorantasena.model.ProsesManual;
import id.co.telkomsigma.monitorantasena.repository.ProsesManualRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import java.sql.Date;
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class ProsesManualService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProsesManualService.class);

    private ProsesManualRepository prosesManualRepository;

    @Autowired
    public ProsesManualService(
            ProsesManualRepository prosesManualRepository
    ) {
        this.prosesManualRepository = prosesManualRepository;
    }

    public void save(ProsesManualRequestDTO prosesManualRequestDTO) {
        ProsesManual prosesManual = null;
        Optional<ProsesManual> prosesManualOpt = prosesManualRepository.findById(prosesManualRequestDTO.getNamaProses());

        prosesManual = prosesManualOpt.get();
        prosesManual.setTglProses(Date.valueOf(prosesManualRequestDTO.getTglProses()));
        prosesManual.setStatusData("aktif");

        ProsesManual saved = prosesManualRepository.save(prosesManual);
        LOGGER.info("Successfully save application with id: " + saved.getNamaProses());
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        Specification<ProsesManual> namaProsesSpecification = (Specification<ProsesManual>) (root, query, criteriaBuilder) -> {
            Expression<String> namaProsesExpression = criteriaBuilder.lower(root.get("namaProses"));
            return criteriaBuilder.equal(namaProsesExpression, "antasena");
        };

        return prosesManualRepository.findAll(dataTablesInput, namaProsesSpecification);
    }

    public ProsesManualResponseDTO findById(String id) {
        ProsesManualResponseDTO prosesManualResponseDTO = null;
        Optional<ProsesManual> prosesManualOpt = prosesManualRepository.findById(id);
        if (prosesManualOpt.isPresent()) {
            ProsesManual prosesManual = prosesManualOpt.get();

            prosesManualResponseDTO = new ProsesManualResponseDTO(prosesManual);
        }

        return prosesManualResponseDTO;
    }
}
