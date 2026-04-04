package models;
import models.MotorbikeModel;

public class MotorbikeVersion {
    private int id;
    private String color;// màu xe
    private String engineCapacity;// phân khối
    private String price;// giá
    private MotorbikeModel model;
    public MotorbikeVersion(int id, String color,String engineCapacity,String price, MotorbikeModel model){
        this.id=id;
        this.color=color;
        this.engineCapacity=engineCapacity;
        this.price=price;
        this.model=model;
    }
    public int getId() { return id; }   
    public MotorbikeModel getmodel(){return model;}
    public String getcolor(){return color;}
    public String getengineCapacity(){return engineCapacity;}
    public String getprice(){return price;}
    // Getter

}