package models;
import java.sql.Date;

import models.MotorbikeVersion;

enum Status {
    IN_STOCK, SOLD //các giá trị cố định của kiểu status
}
public class MotorbikeInstance {
    private String vin;
    private String engineNumber;
    private Status status;
    private MotorbikeVersion version;
    private Date importDate;

    public MotorbikeInstance(String vin, String engineNumber,MotorbikeVersion version, Date importDate) {
        this.vin = vin;
        this.engineNumber = engineNumber;
        this.version=version;
        this.status = Status.IN_STOCK;
        this.importDate = importDate;

    }
    public MotorbikeVersion getversion(){
        return version;
    }
    public String getVin() { return vin; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "VIN: " + vin +" | Engine: " + engineNumber +
        " | bikename: " + version.getmodel().getmodelname() +
         " | Color: " + version.getcolor() + " | Status: " + status;
    }
}