package services;

import models.MotorbikeInstance;
import utils.MotorbikeInstanceDao;
import java.util.ArrayList;

public class checkStatusWareHouse {
    
    public void checkStatus(String modelNameFindout) {
        // Gọi DAO để lấy danh sách xe theo tên model từ Database
        ArrayList<MotorbikeInstance> results = MotorbikeInstanceDao.getInstance().selectByModelName(modelNameFindout);

        if (results.isEmpty()) {
            System.out.println("Cannot find any " + modelNameFindout + " in the warehouse.");
        } else {
            System.out.println("Found " + results.size() + " " + modelNameFindout + "s in the warehouse:");
            for (MotorbikeInstance motor : results) {
                motor.showInforMotor();
                System.out.println("----------------------------");
            }
        }
    }
}