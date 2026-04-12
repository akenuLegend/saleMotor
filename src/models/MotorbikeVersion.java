package models;
import models.MotorbikeModel;

public class MotorbikeVersion extends MotorbikeModel {
    private String color;// màu xe
    private String engineCapacity;// phân khối
    private String price;// giá
    private MotorbikeModel model;
    private int versionID;
    public MotorbikeVersion( int vid ,String color,String engineCapacity,String price, MotorbikeModel model){
        super(model.getmodelId(),model.getmodelname(),model.getbrand(),model.gettype(),model.getdescription());
        this.versionID = vid;
        this.color=color;
        this.engineCapacity=engineCapacity;
        this.price=price;
        this.model=model;
    }
    public MotorbikeModel getmodel(){return model;}
    public String getcolor(){return color;}
    public String getengineCapacity(){return engineCapacity;}
    public String getprice(){return price;}
    public void setColor(String color) {
        this.color = color;
    }
    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setModel(MotorbikeModel model) {
        this.model = model;
    }

    public void setVersionID(int versionID) {
        this.versionID = versionID;
    }
    public int getVersionID() {
        return versionID;
    }

    public void showInforModels() {
        System.out.println("Color: " + color);
        System.out.println("Engine Capacity: " + engineCapacity);
        System.out.println("Price: " + price);
        System.out.println("Model Information:");
        System.out.println("  Model ID: " + model.getmodelId());
        System.out.println("  Model Name: " + model.getmodelname());
        System.out.println("  Brand: " + model.getbrand());
    }

}