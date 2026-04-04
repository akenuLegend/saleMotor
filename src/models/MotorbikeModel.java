package models;
public class MotorbikeModel {
    private String modelId;// ma xe
    private String modelName;// ten xe
    private String brand;//hãng
    private String type;
    private String description;
     public MotorbikeModel(String modelId,String modelName,String brand, String type, String description ){
        this.modelId=modelId;
        this.modelName=modelName;
        this.brand=brand;
        this.type=type;
        this.description=description;
    }
    public String getmodelId(){return modelId;}
    public String getmodelname(){return modelName;}
    public String getbrand(){return brand;}
    public String tostring(){
        return "mã xe : "+ modelId +" tên xe : "+modelName+" hãng xe : "+brand;
    }
}