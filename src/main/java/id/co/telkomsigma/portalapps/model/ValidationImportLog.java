package id.co.telkomsigma.portalapps.model;

import javax.persistence.*;

/**
 * @author satiya
 */
@Entity
@Table(name = "validation_import_log")
public class ValidationImportLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "angka_row")
    private String rowNumber;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "description")
    private String description;

    public ValidationImportLog() {
    }

    public ValidationImportLog(String rowNumber, String columnName, String description) {
        this.rowNumber = rowNumber;
        this.columnName = columnName;
        this.description = description;
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
