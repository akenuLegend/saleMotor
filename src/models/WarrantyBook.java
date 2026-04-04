package models;

public class WarrantyBook {
    private String vin;
    private int customerId;
    private Date issueDate;
    private Date expDate;

    public WarrantyBook( String vin, int customerId, Date issueDate, Date expDate) {
        this.vin = vin;
        this.customerId = customerId;
        this.issueDate = issueDate;
        this.expDate = expDate;
    }

    public void checkWarranty() {
       
    }
}