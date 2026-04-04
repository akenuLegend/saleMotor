package models;

public class SaleOrder {
    private int id;
    private int customerId;
    private Date orderDate;
    private String paymentStatus;

    public SaleOrder(int id, int customerId, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.paymentStatus = "PAID";
    }

}