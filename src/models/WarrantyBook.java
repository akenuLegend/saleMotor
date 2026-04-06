package models;
import java.sql.Date;
import java.time.LocalDate;

public class WarrantyBook {
    private String vin;
    private int customerId;
    private LocalDate issueDate;
    private LocalDate expDate;

    public WarrantyBook( String vin, int customerId, LocalDate issueDate, LocalDate expDate) {
        this.vin = vin;
        this.customerId = customerId;
        this.issueDate = issueDate;
        this.expDate = expDate;
    }

    public void checkWarranty() {
       
    }
}