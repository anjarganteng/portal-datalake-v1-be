package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CashbackRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CashbackResponseDTO;
import id.co.telkomsigma.portalapps.model.Cashback;
import id.co.telkomsigma.portalapps.repository.CashbackRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
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
public class CashbackService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CashbackService.class);

    private CashbackRepository cashbackRepository;

    @Autowired
    public CashbackService(
            CashbackRepository cashbackRepository
    ) {
        this.cashbackRepository = cashbackRepository;
    }

    public void save(CashbackRequestDTO cashbackRequestDTO) {
        Cashback cashback = null;
        Optional<Cashback> cashbackOpt = cashbackRepository.findById(cashbackRequestDTO.getNoRek());

        if (cashbackRequestDTO.isNew()) {
            // do insert
            if (cashbackOpt.isPresent()) {
                throw new DuplicateDataException("NO REK you entered already exist!");
            } else {
                cashback = new Cashback(cashbackRequestDTO);
            }
        } else {
            // do update
            cashback = cashbackOpt.get();
            cashback.setJnsSimpanan(cashbackRequestDTO.getJnsSimpanan());
            cashback.setNominalCashback(cashbackRequestDTO.getNominalCashback());
            cashback.setPersentaseCashback(cashbackRequestDTO.getPersentaseCashback());
        }

        cashbackRepository.save(cashback);
        LOGGER.info("Successfully save application with id: " + cashbackRequestDTO.getNoRek());
    }

    public List<CashbackResponseDTO> getAll() {
        List<CashbackResponseDTO> cashbackResponseDTOs = null;
        List<Cashback> cashbacks = cashbackRepository.findAll();

        if (!cashbacks.isEmpty()) {
            cashbackResponseDTOs = new ArrayList<>();
            for (Cashback cashback : cashbacks) {
                CashbackResponseDTO cashbackResponseDTO = new CashbackResponseDTO(cashback);
                cashbackResponseDTOs.add(cashbackResponseDTO);
            }

        }

        return cashbackResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CashbackResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Cashback> dataTablesOutput = cashbackRepository.findAll(dataTablesInput);

        List<CashbackResponseDTO> cashbackResponseDTOS = new ArrayList<>();
        for (Cashback cashback : dataTablesOutput.getData()) {
            CashbackResponseDTO cashbackResponseDTO = new CashbackResponseDTO(cashback);

            cashbackResponseDTOS.add(cashbackResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(cashbackResponseDTOS);

        return result;
    }

    public CashbackResponseDTO findById(String id) {
        CashbackResponseDTO cashbackResponseDTO = null;
        Optional<Cashback> cashbackOpt = cashbackRepository.findById(id);
        if (cashbackOpt.isPresent()) {
            Cashback cashback = cashbackOpt.get();

            cashbackResponseDTO = new CashbackResponseDTO(cashback);
        }

        return cashbackResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Cashback> cashbackOpt = cashbackRepository.findById(id);
        if (cashbackOpt.isPresent()) {
            cashbackRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Cashback> cashbacks = cashbackRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.cashbackToExcel(cashbacks);
        return in;
    }
}
