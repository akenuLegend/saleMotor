package models;
import java.time.LocalDate;
import java.sql.Date;

public class SaleOrder {
    public  static int id = 0;
    private int customerId;
    private LocalDate orderDate;
    private String paymentStatus;

    public SaleOrder( int customerId, LocalDate orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.paymentStatus = "PAID";
        id++;
    }


    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}