package models;


public class MotorbikeModel {
    private int modelId;// ma xe
    private String modelName;// ten xe
    private String brand;//hãng
    private String type;
    private String description;
     public MotorbikeModel(int modelId,String modelName,String brand, String type, String description ){
        this.modelId=modelId;
        this.modelName=modelName;
        this.brand=brand;
        this.type=type;
        this.description=description;
    }
    public int getmodelId(){return modelId;}
    public String getmodelname(){return modelName;}
    public String getbrand(){return brand;}
    public String gettype(){return type;}
    public String getdescription(){return description;}
    public void setmodelId(int modelId) {
        this.modelId = modelId;
    }
    public void setmodelName(String modelName) {
        this.modelName = modelName;
    }
    public void setbrand(String brand) {
        this.brand = brand;
    }
    public void settype(String type) {
        this.type = type;
    }
    public void setdescription(String description) {
        this.description = description;
    }

    public String tostring(){
        return "mã xe : "+ modelId +" tên xe : "+modelName+" hãng xe : "+brand;
    }
}