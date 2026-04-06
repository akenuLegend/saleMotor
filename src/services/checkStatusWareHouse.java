package services;

import models.MotorbikeInstance;
import services.MotorImportService;

public class checkStatusWareHouse extends MotorImportService {
    
    public void checkStatus( String modelNameFindout, MotorImportService motorImportService) {
        for (MotorbikeInstance motor : motorImportService.listMotor) {
            if (motor.getVersion().getmodel().getmodelname().equals(modelNameFindout)) {
                motor.showInforMotor();
                return ;
            }
        }
        System.out.println("Không tìm thấy mẫu xe " + modelNameFindout + " trong kho."); // Thông báo nếu không tìm thấy mẫu xe
        
    }

}