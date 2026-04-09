package models;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;

public class WarrantyVisit {
    private int id;
    private String vin;
    private LocalDate visitDate;
    private int kmReading;
    public static String[] error = { "loi dong co", " loi den tin hieu", "loi he thong dieu khien"};;
    private String technicianNotes;

    public WarrantyVisit(int id, String vin , LocalDate visitDate, int kmReading, String notes) {
        this.id = id;
        this.vin = vin;
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
    public String getVin() {
        return vin;
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
    public void setVin(String vin) {
        this.vin = vin;
    }
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
    public void setKmReading(int kmReading) {
        this.kmReading = kmReading;
    }
    
}