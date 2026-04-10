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
        } else {
            System.out.println("Model already exists!");
        }
    }

    public void deleteModel(MotorbikeModel model) {
        if (MotorbikeModelDao.getInstance().delete(model) > 0) {
            System.out.println("Delete model successfully!");
        } else {
            System.out.println("Model not found!");
        }
    }

    public void addNewVersion(MotorbikeVersion version) {
        if (MotorbikeVersionDao.getInstance().insert(version) > 0) {
            System.out.println("Add new version successfully!");
        } else {
            System.out.println("Version already exists for this model and color!");
        }
    }

    public void deleteVersion(MotorbikeVersion version) {
        if (MotorbikeVersionDao.getInstance().delete(version) > 0) {
            System.out.println("Delete version successfully!");
        } else {
            System.out.println("Version not found for this model and color!");
        }
    }


    public void addNewMotorbike(MotorbikeInstance instance) {
        if (MotorbikeInstanceDao.getInstance().insert(instance) > 0) {
            System.out.println("Import motorbike VIN: " + instance.getVin() + " successfully!");
        }
        else {
            System.out.println("Motorbike with VIN: " + instance.getVin() + " already exists in the system!");
        }
    }

    public void deleteMotorbike(MotorbikeInstance instance) {
        if (MotorbikeInstanceDao.getInstance().delete(instance) > 0) {
            System.out.println("Removed motorbike VIN: " + instance.getVin() + " from database!");
        } else {
            System.out.println("Motorbike with VIN: " + instance.getVin() + " not found in the system!");
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
        else {
            System.out.println("Failed to save purchase order. Please check the details and try again.");
        }
    }

    public void deletePurchaseOrder(PurchaseOrder order) {
        if (PurchaseOrderDao.getInstance().delete(order) > 0) {
            System.out.println("ID: " + order.getid() + " deleted successfully!");
        } 
        else {
            System.out.println("Failed to delete purchase order. Please check if the order exists and try again.");
        }
    }
  
}