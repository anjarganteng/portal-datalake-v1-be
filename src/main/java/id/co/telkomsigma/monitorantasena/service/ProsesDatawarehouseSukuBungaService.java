package id.co.telkomsigma.monitorantasena.service;

import id.co.telkomsigma.monitorantasena.model.ProsesUmum;
import id.co.telkomsigma.monitorantasena.repository.ProsesUmumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import java.util.Optional;

/**
 * @author satiya
 */
@Service
public class ProsesDatawarehouseSukuBungaService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProsesDatawarehouseSukuBungaService.class);

    private ProsesUmumRepository prosesUmumRepository;

    @Autowired
    public ProsesDatawarehouseSukuBungaService(
            ProsesUmumRepository prosesUmumRepository
    ) {
        this.prosesUmumRepository = prosesUmumRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        Specification<ProsesUmum> namaProsesSpecification = (Specification<ProsesUmum>) (root, query, criteriaBuilder) -> {
            Expression<String> namaProsesExpression = criteriaBuilder.lower(root.get("namaProses"));
            return criteriaBuilder.equal(namaProsesExpression, "datawarehouse_SukuBunga_NG");
        };

        return prosesUmumRepository.findAll(dataTablesInput, namaProsesSpecification);
    }

    public void manualTrigger(String namaProses) {
        Optional<ProsesUmum> prosesUmumOpt = prosesUmumRepository.findById(namaProses);
        if (prosesUmumOpt.isPresent()) {
            ProsesUmum prosesUmum = prosesUmumOpt.get();

            if (!"selesai".equals(prosesUmum.getStatusProses())) {
                throw new RuntimeException("Still Being Processed");
            } else {
                if (!"aktif".equals(prosesUmum.getStatusData())) {
                    // do update
                    prosesUmum.setStatusData("aktif");
                } else {
                    throw new RuntimeException("Still Being Processed");
                }
            }
            prosesUmumRepository.save(prosesUmum);
            LOGGER.info("Successfully manual trigger");
        } else {
            LOGGER.info("Can't manual trigger");
            throw new RuntimeException("Can't manual trigger");
        }
    }
}
