package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.exception.DuplicateDataException;
import id.co.telkomsigma.portalapps.dto.request.MdrRequestDTO;
import id.co.telkomsigma.portalapps.dto.response.MdrResponseDTO;
import id.co.telkomsigma.portalapps.model.Mdr;
import id.co.telkomsigma.portalapps.repository.MdrRepository;
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
public class MdrService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MdrService.class);

    private MdrRepository mdrRepository;

    @Autowired
    public MdrService(
            MdrRepository mdrRepository
    ) {
        this.mdrRepository = mdrRepository;
    }

    public void save(MdrRequestDTO mdrRequestDTO) {
        Mdr mdr = null;
        Optional<Mdr> mdrOpt = mdrRepository.findById(mdrRequestDTO.getKodeMerchant());

        if (mdrRequestDTO.isNew()) {
            // do insert
            if (mdrOpt.isPresent()) {
                throw new DuplicateDataException("Kode Merchant you entered already exist!");
            } else {
                mdr = new Mdr(mdrRequestDTO);
            }
        } else {
            // do update
            mdr = mdrOpt.get();
            mdr.setRate(mdrRequestDTO.getRate());
        }

        mdrRepository.save(mdr);
        LOGGER.info("Successfully save application with id: " + mdrRequestDTO.getKodeMerchant());
    }

    public List<MdrResponseDTO> getAll() {
        List<MdrResponseDTO> mdrResponseDTOs = null;
        List<Mdr> mdrs = mdrRepository.findAll();

        if (!mdrs.isEmpty()) {
            mdrResponseDTOs = new ArrayList<>();
            for (Mdr mdr : mdrs) {
                MdrResponseDTO mdrResponseDTO = new MdrResponseDTO(mdr);
                mdrResponseDTOs.add(mdrResponseDTO);
            }

        }

        return mdrResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<MdrResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<Mdr> dataTablesOutput = mdrRepository.findAll(dataTablesInput);

        List<MdrResponseDTO> mdrResponseDTOS = new ArrayList<>();
        for (Mdr mdr : dataTablesOutput.getData()) {
            MdrResponseDTO mdrResponseDTO = new MdrResponseDTO(mdr);

            mdrResponseDTOS.add(mdrResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(mdrResponseDTOS);

        return result;
    }

    public MdrResponseDTO findById(String id) {
        MdrResponseDTO mdrResponseDTO = null;
        Optional<Mdr> mdrOpt = mdrRepository.findById(id);
        if (mdrOpt.isPresent()) {
            Mdr mdr = mdrOpt.get();

            mdrResponseDTO = new MdrResponseDTO(mdr);
        }

        return mdrResponseDTO;
    }

    public void deleteById(String id) {
        Optional<Mdr> mdrOpt = mdrRepository.findById(id);
        if (mdrOpt.isPresent()) {
            mdrRepository.deleteById(id);
            LOGGER.info("Successfully delete application with id: " + id);
        } else {
            LOGGER.info("Cannot find application with id: " + id);
        }
    }

    public ByteArrayInputStream loadToExcel() {
        List<Mdr> mdrs = mdrRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.mdrToExcel(mdrs);
        return in;
    }
}
