package id.co.telkomsigma.portalapps.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * @author satiya
 */
@Entity
@Table(name = "import_excel_log")
public class ImportExcelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "input_date")
    private Date inputDate;

    @Column(name = "input_time")
    private Time inputTime;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "user")
    private String user;

    @Column(name = "status")
    private String status;

    @Column(name = "form")
    private String form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Time getInputTime() {
        return inputTime;
    }

    public void setInputTime(Time inputTime) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}
