package models;
import java.time.LocalDate;
import java.sql.Date;

public class SaleOrderDetail {
    private int orderId; // Lưu ID của hóa đơn chứa nó
    private String vin;
    private int salePrice;

    public SaleOrderDetail(int orderId, String vin, int salePrice) {
        this.orderId = orderId;
        this.vin = vin;
        this.salePrice = salePrice;
    }

    public int getOrderId() {
        return orderId;
    }
    public String getVin() {
        return vin;
    }
    public int getSalePrice() {
        return salePrice;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
}