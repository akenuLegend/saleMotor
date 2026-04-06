package models;
import models.MotorbikeModel;

public class MotorbikeVersion extends MotorbikeModel {
    private String color;// màu xe
    private String phan_khoi;// phân khối
    private String price;// giá
    private MotorbikeModel model;
    public MotorbikeVersion(String color,String phan_khoi,String price, MotorbikeModel model){
        super(model.getmodelId(),model.getmodelname(),model.getbrand(),model.gettype(),model.getdescription());
        this.color=color;
        this.phan_khoi=phan_khoi;
        this.price=price;
        this.model=model;
    }
    public MotorbikeModel getmodel(){return model;}
    public String getcolor(){return color;}
    public String getphankhoi(){return phan_khoi;}
    public String getprice(){return price;}
    public void setColor(String color) {
        this.color = color;
    }
    public void setPhanKhoi(String phan_khoi) {
        this.phan_khoi = phan_khoi;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setModel(MotorbikeModel model) {
        this.model = model;
    }

    public void showInforModels() {
        System.out.println("Màu xe: " + color);
        System.out.println("Phân khối: " + phan_khoi);
        System.out.println("Giá: " + price);
        System.out.println("Thông tin mẫu xe:");
        System.out.println("  Mã xe: " + model.getmodelId());
        System.out.println("  Tên xe: " + model.getmodelname());
        System.out.println("  Hãng xe: " + model.getbrand());
    }

    // public String tostring(){
    //     return "mã xe : "+ modelId +" tên xe : "+modelName+" hãng xe : "+brand;
    // }

}