package services;
import models.MotorbikeInstance;
import models.MotorbikeModel;
import models.MotorbikeVersion;
import models.PurchaseOrder;
import java.util.ArrayList;
import utils.MotorbikeInstanceDao;
import utils.MotorbikeModelDao;
import utils.MotorbikeVersionDao;
import utils.PurchaseOrderDao;


public class MotorImportService {


    public void addNewModel(MotorbikeModel model) {
        if (MotorbikeModelDao.getInstance().insert(model) > 0) {
            System.out.println("Add new model successfully!");
        }
    }

    public void deleteModel(MotorbikeModel model) {
        if (MotorbikeModelDao.getInstance().delete(model) > 0) {
            System.out.println("Delete model successfully!");
        } 
    }

    public void addNewVersion(MotorbikeVersion version) {
        if (MotorbikeVersionDao.getInstance().insert(version) > 0) {
            System.out.println("Add new version successfully!");
        }
    }

    public void deleteVersion(MotorbikeVersion version) {
        if (MotorbikeVersionDao.getInstance().delete(version) > 0) {
            System.out.println("Delete version successfully!");
        } 
    }


    public void addNewMotorbike(MotorbikeInstance instance) {
        if (MotorbikeInstanceDao.getInstance().insert(instance) > 0) {
            System.out.println("Import motorbike VIN: " + instance.getVin() + " successfully!");
        }
    }

    public void deleteMotorbike(MotorbikeInstance instance) {
        if (MotorbikeInstanceDao.getInstance().delete(instance) > 0) {
            System.out.println("Removed motorbike VIN: " + instance.getVin() + " from database!");
        } 
    }

    public void showListMotor() {
        ArrayList<MotorbikeInstance> list = MotorbikeInstanceDao.getInstance().selectAll();
        if (list.isEmpty()) {
            System.out.println("Empty!!!");
        } else {
            System.out.println("--- List Motorbikes ---");
            for (MotorbikeInstance i : list) {
                // Sử dụng hàm toString() cậu đã viết rất đẹp ở trên
                System.out.println(i.toString());
            }
        }
    }

    public void addPurchaseOrder(PurchaseOrder order) {
    if (PurchaseOrderDao.getInstance().insert(order) > 0) {
            System.out.println("ID: " + order.getid() + " saved successfully!");
        }
    }

    public void deletePurchaseOrder(PurchaseOrder order) {
        if (PurchaseOrderDao.getInstance().delete(order) > 0) {
            System.out.println("ID: " + order.getid() + " deleted successfully!");
        } 
    }
  
}