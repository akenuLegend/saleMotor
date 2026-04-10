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

    public Customer(String fullName, String phone, int id, String identityCard, LocalDate dateOfBirth, String address, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.id = id;
        this.identityCard = identityCard;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
    }

    public void showInforCustomer() {
        System.out.println("Name: " + fullName);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("NumVehicle: " + numVehicle);
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