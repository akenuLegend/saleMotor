package models;
import java.time.LocalDate;
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
        System.out.println("Thông tin xe máy:");
        System.out.println("VIN: " + vin);
        System.out.println("Số máy: " + engineNumber);
        System.out.println("Phiên bản: " + version.getmodel().getmodelname() + " - " + version.getcolor());
        System.out.println("Trạng thái: " + status);
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