package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.request.ApplicationBranchRequestDTO;
import id.co.telkomsigma.portalapps.dto.request.ApplicationBranchRequestOriginalDTO;
import id.co.telkomsigma.portalapps.dto.request.Select2RequestDTO;
import id.co.telkomsigma.portalapps.dto.response.ApplicationBranchResponseDTO;
import id.co.telkomsigma.portalapps.dto.response.Select2ResponseDTO;
import id.co.telkomsigma.portalapps.model.ApplicationBranch;
import id.co.telkomsigma.portalapps.repository.ApplicationBranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author satiya
 */
@Service
public class ApplicationBranchService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBranchService.class);

    private ApplicationBranchRepository applicationBranchRepository;

    @Autowired
    public ApplicationBranchService(
            ApplicationBranchRepository applicationBranchRepository
    ) {
        this.applicationBranchRepository = applicationBranchRepository;
    }

    public void save(ApplicationBranchRequestDTO applicationBranchRequestDTO) {
        ApplicationBranch applicationBranch = null;
        if (applicationBranchRequestDTO.getId() != null) {
            // do update
            applicationBranch = applicationBranchRepository.findById(UUID.fromString(applicationBranchRequestDTO.getId())).get();
            applicationBranch.setNameBranch(applicationBranchRequestDTO.getText());
        } else {
            // do insert
            applicationBranch = new ApplicationBranch(applicationBranchRequestDTO);
        }

        ApplicationBranch saved = applicationBranchRepository.save(applicationBranch);
        LOGGER.info("Successfully save ApplicationBranch with uuid_branch: " + saved.getUuidBranch());
    }

    public void save(ApplicationBranchRequestOriginalDTO requestDTO) {
        ApplicationBranch applicationBranch = null;

        if (requestDTO.isNew()) {
            if (requestDTO.getUuidBranch() == null) {
                // do insert
                applicationBranch = new ApplicationBranch(requestDTO);
            }
        } else {
            Optional<ApplicationBranch> applicationBranchOpt = applicationBranchRepository.findById(UUID.fromString(requestDTO.getUuidBranch()));
            // do update
            applicationBranch = applicationBranchOpt.get();
            applicationBranch.setNameBranch(requestDTO.getNameBranch());
        }

        ApplicationBranch saved = applicationBranchRepository.save(applicationBranch);
        LOGGER.info("Successfully save ApplicationBranch with uuid_branch: " + saved.getUuidBranch());
    }

    public List<ApplicationBranchResponseDTO> getAll() {
        List<ApplicationBranchResponseDTO> applicationBranchResponseDTOs = null;
        List<ApplicationBranch> applicationBranches = applicationBranchRepository.findAll();
        if (!applicationBranches.isEmpty()) {
            applicationBranchResponseDTOs = new ArrayList<>();
            for (ApplicationBranch applicationBranch : applicationBranches) {

                // convert ApplicationBranch into ApplicationBranchResponseDTO
                ApplicationBranchResponseDTO applicationBranchResponseDTO = new ApplicationBranchResponseDTO(applicationBranch);

                applicationBranchResponseDTOs.add(applicationBranchResponseDTO);
            }
        }

        return applicationBranchResponseDTOs;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<ApplicationBranchResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<ApplicationBranch> dataTablesOutput = applicationBranchRepository.findAll(dataTablesInput);

        List<ApplicationBranchResponseDTO> applicationBranchResponseDTOs = new ArrayList<>();
        for (ApplicationBranch applicationBranch : dataTablesOutput.getData()) {

            // convert ApplicationBranch into ApplicationBranchResponseDTO
            ApplicationBranchResponseDTO applicationBranchResponseDTO = new ApplicationBranchResponseDTO(applicationBranch);

            applicationBranchResponseDTOs.add(applicationBranchResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(applicationBranchResponseDTOs);

        return result;
    }

    public ApplicationBranchResponseDTO findById(String uuidBranch) {
        ApplicationBranchResponseDTO applicationBranchResponseDTO = null;
        Optional<ApplicationBranch> applicationBranchOpt = applicationBranchRepository.findById(UUID.fromString(uuidBranch));
        if (applicationBranchOpt.isPresent()) {
            ApplicationBranch applicationBranch = applicationBranchOpt.get();

            // convert ApplicationBranch into ApplicationBranchResponseDTO
            applicationBranchResponseDTO = new ApplicationBranchResponseDTO(applicationBranch);
        }

        return applicationBranchResponseDTO;
    }

    public void deleteById(String uuidBranch) {
        Optional<ApplicationBranch> applicationBranchOpt = applicationBranchRepository.findById(UUID.fromString(uuidBranch));
        if (applicationBranchOpt.isPresent()) {
            applicationBranchRepository.deleteById(UUID.fromString(uuidBranch));
            LOGGER.info("Sucessfully delete ApplicationBranch with uuid_branch: " + uuidBranch);
        } else {
            LOGGER.info("Cannot find ApplicationBranch with uuid_branch: " + uuidBranch);
        }
    }

    public List<Select2ResponseDTO> getAllSelect2(Select2RequestDTO select2RequestDTO) {
        List<ApplicationBranch> applicationBranches = new ArrayList<ApplicationBranch>();
        PageRequest pageRequest = PageRequest.of(select2RequestDTO.getPage(), 5);

        if (select2RequestDTO.getSearch().getText() != null) {
            if (select2RequestDTO.getSearch().getText().equals("")) {
                applicationBranches = applicationBranchRepository.findAll(pageRequest).getContent();
            } else {
                applicationBranches = applicationBranchRepository.findByNameBranchContaining(select2RequestDTO.getSearch().getText(), pageRequest);
            }
        } else {
            applicationBranches = applicationBranchRepository.findAll(pageRequest).getContent();
        }

        List<Select2ResponseDTO> select2ResponseDTOs = new ArrayList<Select2ResponseDTO>();
        for (ApplicationBranch applicationBranch : applicationBranches) {
            select2ResponseDTOs.add(applicationBranch.toSelect2ResponseDTO());
        }

        return select2ResponseDTOs;
    }
}

