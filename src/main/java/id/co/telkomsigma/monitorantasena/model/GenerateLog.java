package id.co.telkomsigma.monitorantasena.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * @author satiya
 */
@Entity
@Table(name = "generate_log")
public class GenerateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "process_date")
    private Date processDate;

    @Column(name = "process_time")
    private Time processTime;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "user")
    private String user;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Time getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Time processTime) {
        this.processTime = processTime;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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
}
