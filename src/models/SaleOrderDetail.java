package models;
import java.time.LocalDate;
import java.sql.Date;

public class SaleOrderDetail extends SaleOrder {
    private String vin;
    private int salePrice;

    public SaleOrderDetail(int idOrder, int cusId, String vin, int salePrice) {
        super(idOrder, cusId, LocalDate.now()); // Call the constructor of the parent class
        this.vin = vin;
        this.salePrice = salePrice;
    }
        public int getOrderId() {
            return super.getId(); // Lấy order_id từ class cha SaleOrder
        }

    public String getVin() {
        return vin;
    }
    public int getSalePrice() {
        return salePrice;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

}