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

    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getsupplier() {
        return supplier;
    }
    public void setsupplier(String supplier) {
        this.supplier = supplier;
    }
    public LocalDate getorderDate() {
        return orderDate;
    }
    public void setorderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}