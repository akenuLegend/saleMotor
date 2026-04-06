package models;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;

public class WarrantyVisit {
    private int id;
    private int warrantyBookId;
    private LocalDate visitDate;
    private int kmReading;
    public static String[] error = { "loi dong co", " loi den tin hieu", "loi he thong dieu khien"};;
    private String technicianNotes;

    public WarrantyVisit(int id, int warrantyBookId, LocalDate visitDate, int kmReading, String notes) {
        this.id = id;
        this.warrantyBookId = warrantyBookId;
        this.visitDate = visitDate;
        this.kmReading = kmReading;
        this.technicianNotes = notes;
    }

    // Các hàm Getter/Setter cơ bản
    public void setTechnicianNotes(String notes) { this.technicianNotes = notes; }

    public String getTechnicianNotes() { return technicianNotes; }
    public int getId() {
        return id;
    }
    public int getWarrantyBookId() {
        return warrantyBookId;
    }
    public LocalDate getVisitDate() {
        return visitDate;
    }
    public int getKmReading() {
        return kmReading;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setWarrantyBookId(int warrantyBookId) {
        this.warrantyBookId = warrantyBookId;
    }
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
    public void setKmReading(int kmReading) {
        this.kmReading = kmReading;
    }
    
}