package models;
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

    public String getVin() {
        return vin;
    }
    public int getCustomerId() {
        return customerId;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public LocalDate getExpDate() {
        return expDate;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}