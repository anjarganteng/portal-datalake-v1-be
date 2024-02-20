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
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

// import java.util.function.Predicate;
import java.util.Optional;

@Service
public class ProsesUmumService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProsesUmumService.class);

    private ProsesUmumRepository prosesUmumRepository;

    @Autowired
    public ProsesUmumService(
            ProsesUmumRepository prosesUmumRepository
    ) {
        this.prosesUmumRepository = prosesUmumRepository;
    }

    //public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        //Specification<ProsesUmum> namaProsesSpecification = (Specification<ProsesUmum>) (root, query, criteriaBuilder) -> {
            //Expression<String> namaProsesExpression = criteriaBuilder.lower(root.get("namaProses"));
            //return criteriaBuilder.equal(namaProsesExpression, "datawarehouse_GL");
        // };

        //return prosesUmumRepository.findAll(dataTablesInput, namaProsesSpecification);
      //  Specification<ProsesUmum> prosesSpecification = (root, query, criteriaBuilder) -> {
        //    Expression<String> namaProsesExpression = criteriaBuilder.lower(root.get("namaProses"));
          //  Expression<Boolean> predicate1 = criteriaBuilder.equal(criteriaBuilder.lower(namaProsesExpression), "datawarehouse_GL_NG");
            //Expression<Boolean> predicate2 = criteriaBuilder.equal(criteriaBuilder.lower(namaProsesExpression), "antasena_GL_NG");
	   // Expression<Boolean> predicate3 = criteriaBuilder.equal(criteriaBuilder.lower(namaProsesExpression), "reporting_service_GL_NG");
            //Predicate predicate1 = criteriaBuilder.equal(namaProsesExpression, "datawarehouse_NG");
            //Predicate predicate2 = criteriaBuilder.equal(namaProsesExpression, "antasena_GL_NG");
            //return criteriaBuilder.or(predicate1, predicate2, predicate3);
           // return criteriaBuilder.or(new Predicate[]{predicate1, predicate2, predicate3});
       // };
        //return prosesUmumRepository.findAll(dataTablesInput, prosesSpecification);
   // }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        Specification<ProsesUmum> prosesSpecification = new Specification<ProsesUmum>() {
            @Override
            public Predicate toPredicate(Root<ProsesUmum> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Expression<String> namaProsesExpression = criteriaBuilder.lower(root.get("namaProses"));
                Predicate predicate1 = criteriaBuilder.equal(namaProsesExpression, "datawarehouse_GL_NG");
                Predicate predicate2 = criteriaBuilder.equal(namaProsesExpression, "antasena_GL_NG");
                Predicate predicate3 = criteriaBuilder.equal(namaProsesExpression, "reporting_service_GL_NG");
                return criteriaBuilder.or(predicate1, predicate2, predicate3);
            }
        };
        return prosesUmumRepository.findAll(dataTablesInput, prosesSpecification);
    }

    public void manualTrigger(String namaProses) {
        Optional<ProsesUmum> prosesUmumOpt2 = prosesUmumRepository.findById("antasena_GL_NG");
        Optional<ProsesUmum> prosesUmumOpt = prosesUmumRepository.findById(namaProses);
        
        ProsesUmum prosesUmum2 = null;
        if (prosesUmumOpt2.isPresent()) {
            prosesUmum2 = prosesUmumOpt2.get();
        }

        if (prosesUmumOpt.isPresent()) {
            ProsesUmum prosesUmum = prosesUmumOpt.get();

            if (!"selesai".equals(prosesUmum2.getStatusProses())) {
                throw new RuntimeException("Generate Data Still Being Processed");
            }

            if (!"selesai".equals(prosesUmum.getStatusProses())) {
                throw new RuntimeException("Ingest Data Still Being Processed");
            } else {
                if (!"aktif".equals(prosesUmum.getStatusData())) {
                    // do update
                    prosesUmum.setStatusData("aktif");
                } else {
                    throw new RuntimeException("Ingest Data Still Being Processed");
                }
                
                if ("aktif".equals(prosesUmum2.getStatusData())) {
                    throw new RuntimeException("Generate Data Still Being Processed"); 
                }
            }
            prosesUmumRepository.save(prosesUmum);
            LOGGER.info("Successfully manual trigger");
        } else {
            LOGGER.info("Can't manual trigger");
            throw new RuntimeException("Can't manual trigger");
        }
    }

    public DataTablesOutput<?> findAllBot(DataTablesInput dataTablesInput) {
        Specification<ProsesUmum> processNameSpecification = (Specification<ProsesUmum>) (root, query, criteriaBuilder) -> {
            Expression<String> processNameExpression = criteriaBuilder.lower(root.get("namaProses"));
            return criteriaBuilder.equal(processNameExpression, "bot_GL_NG");
        };
        return prosesUmumRepository.findAll(dataTablesInput, processNameSpecification);
    }

    public void manualTriggerBot(String namaProses) {
        Optional<ProsesUmum> prosesUmumOpt = prosesUmumRepository.findById(namaProses);
        
        if (prosesUmumOpt.isPresent()) {
            ProsesUmum prosesUmum = prosesUmumOpt.get();

            if (!"selesai".equals(prosesUmum.getStatusProses())) {
                throw new RuntimeException("Generate GL BOT Still Being Processed");
            } else {
                if (!"aktif".equals(prosesUmum.getStatusData())) {
                    // do update
                    prosesUmum.setStatusData("aktif");
                } else {
                    throw new RuntimeException("Generate GL BOT Still Being Processed");
                }
            }
            prosesUmumRepository.save(prosesUmum);
            LOGGER.info("Successfully manual trigger Generate GL BOT");
        } else {
            LOGGER.info("Can't manual trigger Generate GL BOT");
            throw new RuntimeException("Can't manual trigger Generate GL BOT");
        }
    }

}
