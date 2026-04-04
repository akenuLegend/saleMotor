package models;

enum Status {
    IN_STOCK, SOLD //các giá trị cố định của kiểu status
}
public class PurchaseOrder {
    private int id;
    private String supplier;
    private Date orderDate;
    private Status status;

    public PurchaseOrder(int id, String supplier, Date orderDate, int totalAmount) {
        this.id = id;
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = Status.COMPLETED;
    }
}