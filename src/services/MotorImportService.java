package services;
import models.MotorbikeInstance;
import models.PurchaseOrder;
import java.util.ArrayList;

public class MotorImportService {
    public ArrayList<MotorbikeInstance> listMotor = new ArrayList<>();
    public ArrayList<PurchaseOrder> historyImport = new ArrayList<>();

    public void importMotor(MotorbikeInstance motor) {
        listMotor.add(motor);
    }

    public void doPurchase(PurchaseOrder order) {
        historyImport.add(order);
    }

    public void showListMotor() {
        for (MotorbikeInstance motor : listMotor) {
            motor.showInforMotor();
        }
    }

}