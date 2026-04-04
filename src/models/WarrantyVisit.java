package models;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;

public class WarrantyVisit {
    private int id;
    private int warrantyBookId;
    private Date visitDate;
    private int kmReading;
    private ArrayList<String> error;
    private String technicianNotes;

    public WarrantyVisit(int id, int warrantyBookId, Date visitDate, int kmReading, String description) {
        this.id = id;
        this.warrantyBookId = warrantyBookId;
        this.visitDate = visitDate;
        this.kmReading = kmReading;
        this.description = description;
    }

    // Các hàm Getter/Setter cơ bản
    public void setTechnicianNotes(String notes) { this.technicianNotes = notes; }

}