package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.CounterRateSbsRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.CounterRateSbsResponseDTO;
import id.co.telkomsigma.portalapps.model.CounterRateSbs;
import id.co.telkomsigma.portalapps.repository.CounterRateSbsRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import id.co.telkomsigma.util.embeddables.EmbedRateSbs;
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
public class CounterRateSbsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CounterRateSbsService.class);

    private CounterRateSbsRepository counterRateSbsRepository;

    @Autowired
    public CounterRateSbsService(
            CounterRateSbsRepository counterRateSbsRepository
    ) {
        this.counterRateSbsRepository = counterRateSbsRepository;
    }

    public void save(CounterRateSbsRequestDTO counterRateSbsRequestDTO) {
        CounterRateSbs counterRateSbs = null;
        Optional<CounterRateSbs> counterRateSbsOpt = counterRateSbsRepository.findByEmbedRateSbsJenisInstrumenAndEmbedRateSbsJenisValutaAndEmbedRateSbsJangkaWaktu(counterRateSbsRequestDTO.getJenisInstrumen(), counterRateSbsRequestDTO.getJenisValuta(), counterRateSbsRequestDTO.getJangkaWaktu());

        if (counterRateSbsRequestDTO.isNew()) {
            //do insert
            if (counterRateSbsOpt.isPresent()) {
                throw new DuplicateDataException("Jenis Instrument & Jenis Valuta & Jangka Waktu you entered already exist!");
            } else {
                counterRateSbs = new CounterRateSbs(new EmbedRateSbs(counterRateSbsRequestDTO), counterRateSbsRequestDTO);
            }
        } else {
            //do update
            counterRateSbs = counterRateSbsOpt.get();
            counterRateSbs.setRateMin(counterRateSbsRequestDTO.getRateMin());
            counterRateSbs.setRateMax(counterRateSbsRequestDTO.getRateMax());
        }

        CounterRateSbs saved = counterRateSbsRepository.save(counterRateSbs);
        LOGGER.info("Successfully save application with id: " + saved.getEmbedRateSbs().getJenisInstrumen().concat(saved.getEmbedRateSbs().getJenisValuta()));
    }

    public List<CounterRateSbsResponseDTO> getAll() {
        List<CounterRateSbsResponseDTO> counterRateSbsResponseDTOs = null;
        List<CounterRateSbs> counterRateSbss = counterRateSbsRepository.findAll();

        if (!counterRateSbss.isEmpty()) {
            counterRateSbsResponseDTOs = new ArrayList<>();
            for (CounterRateSbs counterRateSbs : counterRateSbss) {
                CounterRateSbsResponseDTO counterRateSbsResponseDTO = new CounterRateSbsResponseDTO(counterRateSbs);
                counterRateSbsResponseDTOs.add(counterRateSbsResponseDTO);
            }

        }

        return counterRateSbsResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<CounterRateSbsResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<CounterRateSbs> dataTablesOutput = counterRateSbsRepository.findAll(dataTablesInput);

        List<CounterRateSbsResponseDTO> counterRateSbsResponseDTOS = new ArrayList<>();
        for (CounterRateSbs counterRateSbs : dataTablesOutput.getData()) {
            CounterRateSbsResponseDTO counterRateSbsResponseDTO = new CounterRateSbsResponseDTO(counterRateSbs);

            counterRateSbsResponseDTOS.add(counterRateSbsResponseDTO);
        }

        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(counterRateSbsResponseDTOS);

        return result;
    }

    public CounterRateSbsResponseDTO findById(String id, String id2, String id3) {
        CounterRateSbsResponseDTO counterRateSbsResponseDTO = null;
        Optional<CounterRateSbs> counterRateSbsOpt = counterRateSbsRepository.findByEmbedRateSbsJenisInstrumenAndEmbedRateSbsJenisValutaAndEmbedRateSbsJangkaWaktu(id, id2, id3);
        if (counterRateSbsOpt.isPresent()) {
            CounterRateSbs counterRateSbs = counterRateSbsOpt.get();

            counterRateSbsResponseDTO = new CounterRateSbsResponseDTO(counterRateSbs);
        }

        return counterRateSbsResponseDTO;
    }

    public void deleteById(String id, String id2, String id3) {
        Optional<CounterRateSbs> counterRateSbsOpt = counterRateSbsRepository.findByEmbedRateSbsJenisInstrumenAndEmbedRateSbsJenisValutaAndEmbedRateSbsJangkaWaktu(id, id2, id3);
        if (counterRateSbsOpt.isPresent()) {
            counterRateSbsRepository.deleteByEmbedRateSbsJenisInstrumenAndEmbedRateSbsJenisValutaAndEmbedRateSbsJangkaWaktu(id, id2, id3);
            LOGGER.info("Successfully delete application with id: " + id.concat(id2));
        } else {
            LOGGER.info("Cannot find application with id: " + id.concat(id2));
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<CounterRateSbs> counterRateSbss = counterRateSbsRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.rateSbsToExcel(counterRateSbss);
        return in;
    }
}
