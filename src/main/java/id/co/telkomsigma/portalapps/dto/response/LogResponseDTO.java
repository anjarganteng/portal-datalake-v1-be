package id.co.telkomsigma.portalapps.dto.response;

import id.co.telkomsigma.portalapps.model.ImportExcelLog;

/**
 * @author satiya
 */
public class LogResponseDTO {

    private static final long serialVersionUID = -3890854155097292410L;

    private String inputDate;
    private String inputTime;
    private String tableName;
    private String fileName;
    private String status;

    public LogResponseDTO() {
    }

    public LogResponseDTO(ImportExcelLog importExcelLog) {
        inputDate = String.valueOf(importExcelLog.getInputDate());
        inputTime = String.valueOf(importExcelLog.getInputTime());
        tableName = importExcelLog.getTableName();
        fileName = importExcelLog.getFileName();
        status = importExcelLog.getStatus();
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
