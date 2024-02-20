package id.co.telkomsigma.portalapps.service;

import id.co.telkomsigma.portalapps.dto.response.MapLoanTypeResponseDTO;
import id.co.telkomsigma.portalapps.model.MapLoanType;
import id.co.telkomsigma.portalapps.repository.MapLoanTypeRepository;
import id.co.telkomsigma.util.ExcelHelperDownload;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

/**
 *
 * @author radit
 */
@Service
public class MapLoanTypeService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MapLoanTypeService.class);
    
    MapLoanTypeRepository mapLoanTypeRepository;

    public MapLoanTypeService(MapLoanTypeRepository mapLoanTypeRepository) {
        this.mapLoanTypeRepository = mapLoanTypeRepository;
    }

    public DataTablesOutput<?> findAll(DataTablesInput dataTablesInput) {
        DataTablesOutput<MapLoanTypeResponseDTO> result = new DataTablesOutput<>();
        DataTablesOutput<MapLoanType> dataTablesOutput = mapLoanTypeRepository.findAll(dataTablesInput);

        List<MapLoanTypeResponseDTO> mapLoanTypeResponseDTOs = new ArrayList<>();
        for (MapLoanType mapLoanType : dataTablesOutput.getData()) {
            MapLoanTypeResponseDTO mapLoanTypeResponseDTO = new MapLoanTypeResponseDTO(mapLoanType);

            mapLoanTypeResponseDTOs.add(mapLoanTypeResponseDTO);
        }
        BeanUtils.copyProperties(dataTablesOutput, result);
        result.setData(mapLoanTypeResponseDTOs);
        return result;
    }

    public ByteArrayInputStream loadToExcel() {
        List<MapLoanType> mapLoanTypes = mapLoanTypeRepository.findAll();

        ByteArrayInputStream in = ExcelHelperDownload.mapLoanTypeToExcel(mapLoanTypes);
        return in;
    }
    
    
    
}
