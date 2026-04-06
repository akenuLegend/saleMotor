package models;
import java.util.ArrayList;
import models.WarrantyVisit;
import java.time.LocalDate;

public class Customer {
    private int id;
    private String identityCard;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String email;
    public static int numVehicle = 0;
    private ArrayList<SaleOrderDetail> historyPurchase = new ArrayList<>();
    private ArrayList<WarrantyVisit> historyWar = new ArrayList<>();

    // Constructor khởi tạo khách hàng mới
    public Customer(String fullName, String phone, int id, String identityCard, LocalDate dateOfBirth, String address, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.id = id;
        this.identityCard = identityCard;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
    }

    public void updateVehicleCount() {
        numVehicle++;
    }

    public void updateWarrantyVisit(WarrantyVisit visit) {
        historyWar.add(visit);
    }

    public void updatePurchaseHistory(SaleOrderDetail orderDetail) {
        historyPurchase.add(orderDetail);
    }

    public void showPurchaseHistory() {
        for (SaleOrderDetail order : historyPurchase) {
            System.out.println("Ngày mua: " + order.getOrderDate() + ", VIN: " + order.getVin() + ", Giá bán: " + order.getSalePrice());
        }
    }

    public void showWarrantyHistory() {
        for (WarrantyVisit visit : historyWar) {
            System.out.println("Ngày: " + visit.getVisitDate() + ", Ghi chú kỹ thuật viên: " + visit.getTechnicianNotes());
        }
    }

    public void showInforCustomer() {
        System.out.println("Tên khách hàng: " + fullName);
        System.out.println("Số điện thoại: " + phone);
        System.out.println("Địa chỉ: " + address);
        System.out.println("Email: " + email);
        System.out.println("Số lượng xe đã mua: " + numVehicle);
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }  
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getIdentityCard() { return identityCard; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setId(int id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setIdentityCard(String identityCard) { this.identityCard = identityCard; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

}