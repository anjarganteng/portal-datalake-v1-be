package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CounterRateSbkRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CounterRateSbkResponseDTO;
import id.co.telkomsigma.portalapps.model.CounterRateSbk;
import id.co.telkomsigma.portalapps.repository.CounterRateSbkRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedRateSbk;
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
public class CounterRateSbkService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CounterRateSbkService.class);

    private CounterRateSbkRepository counterRateSbkRepository;

    @Autowired
    public CounterRateSbkService(
            CounterRateSbkRepository counterRateSbkRepository
    ) {
        this.counterRateSbkRepository = counterRateSbkRepository;
    }

    public void save(CounterRateSbkRequestDTO counterRateSbkRequestDTO) {
        CounterRateSbk counterRateSbk = null;
        Optional<CounterRateSbk> counterRateSbkOpt = counterRateSbkRepository.findByEmbedRateSbkJenisPenggunaanAndEmbedRateSbkJenisValuta(counterRateSbkRequestDTO.getJenisPenggunaan(), counterRateSbkRequestDTO.getJenisValuta());

        if (counterRateSbkRequestDTO.isNew()) {
            //do insert
            if (counterRateSbkOpt.isPresent()) {
                throw new DuplicateDataException("Jenis Penggunaan & Jenis Valuta you entered already exist!");
            } else {
                counterRateSbk = new CounterRateSbk(new EmbedRateSbk(counterRateSbkRequestDTO), counterRateSbkRequestDTO);
            }
        } else {
            //do update
            counterRateSbk = counterRateSbkOpt.get();
            counterRateSbk.setRateFlat(counterRateSbkRequestDTO.getRateFlat());
            counterRateSbk.setRateEfektif(counterRateSbkRequestDTO.getRateEfektif());
        }

        CounterRateSbk saved = counterRateSbkRepository.save(counterRateSbk);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedRateSbk().getJenisPenggunaan().concat(saved.getEmbedRateSbk().getJenisValuta()));
    }

    public List<CounterRateSbkResponseDTO> getAll() {
        List<CounterRateSbkResponseDTO> counterRateSbkResponseDTOs = null;
        List<CounterRateSbk> counterRateSbkss = counterRateSbkRepository.findAll();

        if (!counterRateSbkss.isEmpty()) {
            counterRateSbkResponseDTOs = new ArrayList<>();
            for (CounterRateSbk counterRateSbk : counterRateSbkss) {
                CounterRateSbkResponseDTO counterRateSbkResponseDTO = new CounterRateSbkResponseDTO(counterRateSbk);
                counterRateSbkResponseDTOs.add(counterRateSbkResponseDTO);
            }

        }

        return counterRateSbkResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CounterRateSbkResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<CounterRateSbk> dataTablesOutput = counterRateSbkRepository.findAll(dataTablesInput);

        List<CounterRateSbkResponseDTO> counterRateSbkResponseDTOS = new ArrayList<>();
        for (CounterRateSbk counterRateSbk : dataTablesOutput.getData()) {
            CounterRateSbkResponseDTO counterRateSbkResponseDTO = new CounterRateSbkResponseDTO(counterRateSbk);

            counterRateSbkResponseDTOS.add(counterRateSbkResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(counterRateSbkResponseDTOS);

        return result;
    }

    public CounterRateSbkResponseDTO findById(String id, String id2) {
        CounterRateSbkResponseDTO counterRateSbkResponseDTO = null;
        Optional<CounterRateSbk> counterRateSbkOpt = counterRateSbkRepository.findByEmbedRateSbkJenisPenggunaanAndEmbedRateSbkJenisValuta(id, id2);
        if (counterRateSbkOpt.isPresent()) {
            CounterRateSbk counterRateSbk = counterRateSbkOpt.get();

            counterRateSbkResponseDTO = new CounterRateSbkResponseDTO(counterRateSbk);
        }

        return counterRateSbkResponseDTO;
    }

    public void deleteById(String id, String id2) {
        Optional<CounterRateSbk> counterRateSbkOpt = counterRateSbkRepository.findByEmbedRateSbkJenisPenggunaanAndEmbedRateSbkJenisValuta(id, id2);
        if (counterRateSbkOpt.isPresent()) {
            counterRateSbkRepository.deleteByEmbedRateSbkJenisPenggunaanAndEmbedRateSbkJenisValuta(id, id2);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<CounterRateSbk> counterRateSbks = counterRateSbkRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.rateSbkToExcel(counterRateSbks);
        return in;
    }
}
