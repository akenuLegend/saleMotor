package models;
import java.util.ArrayList;
import models.WarrantyVisit;

class Customer {
    private int id;
    private String identityCard;
    private String fullName;
    private String dateOfBirth;
    private String phone;
    private String address;
    private String email;
    private ArrayList<Integer> numVehicle = new ArrayList<>();
    private ArrayList<WarrantyVisit> historyWar = new ArrayList<>();

    // Constructor khởi tạo khách hàng mới
    public Customer(String fullName, String phone, int id, String identityCard, String dateOfBirth, String address, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.id = id;
        this.identityCard = identityCard;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
    }
}