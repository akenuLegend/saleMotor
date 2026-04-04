package models;

public class SaleOrderDetail extends SaleOrder {
    private String vin;
    private int salePrice;

    public SaleOrderDetail(int id, int orderId, String vin, int salePrice) {
        super(id, orderId, new Date()); // Call the constructor of the parent class
        this.vin = vin;
        this.salePrice = salePrice;
    }
}