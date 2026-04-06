package models;
import java.time.LocalDate;
import java.sql.Date;

enum Status {
    IN_STOCK, SOLD //các giá trị cố định của kiểu status
}
public class PurchaseOrder {
    private int id;
    private String supplier;
    private LocalDate orderDate;
    private Status status;

    public PurchaseOrder(int id, String supplier, LocalDate orderDate) {
        this.id = id;
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.status = Status.IN_STOCK;
    }
}