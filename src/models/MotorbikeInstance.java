package models;
import java.time.LocalDate;
import java.sql.Date;

import models.MotorbikeVersion;

enum Status {
    IN_STOCK, SOLD 
}
public class MotorbikeInstance {
    private String vin;
    private String engineNumber;
    public Status status;
    private MotorbikeVersion version;
    private LocalDate importDate;

    public MotorbikeInstance(String vin, String engineNumber,MotorbikeVersion version, LocalDate importDate) {
        this.vin = vin;
        this.engineNumber = engineNumber;
        this.version=version;
        this.status = Status.IN_STOCK;
        this.importDate = importDate;
    }
    public MotorbikeVersion getversion(){
        return version;
    }

    @Override
    public String toString() {
        return "VIN: " + vin +" | Engine: " + engineNumber +
        " | bikename: " + version.getmodel().getmodelname() +
         " | Color: " + version.getcolor() + " | Status: " + status;
    }


    public void showInforMotor() {
        System.out.println("Infor Motorbike:");
        System.out.println("VIN: " + vin);
        System.out.println("Engine: " + engineNumber);
        System.out.println("Model: " + version.getmodel().getmodelname() + " - " + version.getcolor());
        System.out.println("Status: " + status);
    }


    public void setVIN(String vin) {
        this.vin = vin;
    }
    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }
    public void setVersion(MotorbikeVersion version) {
        this.version = version;
    }
    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }
    public void setStatusSold() {
        this.status = Status.SOLD;
    }
    public LocalDate getImportDate() {
        return importDate;
    }
    public String getEngineNumber() {
        return engineNumber;
    }
    public  Status getStatus() {
        return status;
    }

    public String getVin() {
        return vin;
    }
    public MotorbikeVersion getVersion() {
        return version;
    }


}