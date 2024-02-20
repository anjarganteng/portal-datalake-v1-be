package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ValidationImportLog;

/**
 * @author satiya
 */
public class ValidationImportLogResponseDTO {

    private static final long serialVersionUID = 6641921715057530461L;

    private Long id;
    private String rowNumber;
    private String columnName;
    private String description;

    public ValidationImportLogResponseDTO() {
    }

    public ValidationImportLogResponseDTO(ValidationImportLog validationImportLog) {
        id = validationImportLog.getId();
        rowNumber = validationImportLog.getRowNumber();
        columnName = validationImportLog.getColumnName();
        description = validationImportLog.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
