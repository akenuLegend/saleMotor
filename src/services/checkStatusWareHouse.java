package services;

import models.MotorbikeInstance;
import utils.MotorbikeInstanceDao;
import java.util.ArrayList;

public class checkStatusWareHouse {
    
    public void checkStatus(String modelNameFindout) {
        // Gọi DAO để lấy danh sách xe theo tên model từ Database
        ArrayList<MotorbikeInstance> results = MotorbikeInstanceDao.getInstance().selectByModelName(modelNameFindout);

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy mẫu xe " + modelNameFindout + " còn trong kho.");
        } else {
            System.out.println("Tìm thấy " + results.size() + " xe thuộc mẫu " + modelNameFindout + ":");
            for (MotorbikeInstance motor : results) {
                motor.showInforMotor();
                System.out.println("----------------------------");
            }
        }
    }
}