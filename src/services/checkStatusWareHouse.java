package services;

import models.MotorbikeInstance;
import services.MotorImportService;

public class checkStatusWareHouse extends MotorImportService {
    
    public void checkStatus( String modelNameFindout, MotorImportService motorImportService) {
        boolean isFound = false; // Thêm biến để đánh dấu xem có tìm thấy xe nào không

        for (MotorbikeInstance motor : motorImportService.listMotor) {
            if (motor.getVersion().getmodel().getmodelname().equals(modelNameFindout)) {
                motor.showInforMotor();
                isFound = true; // Đánh dấu là đã tìm thấy ít nhất 1 chiếc
                // Không dùng return ở đây nữa để nó duyệt hết danh sách
            }
        }
        
        // Sau khi duyệt hết kho, nếu biến isFound vẫn là false thì mới in thông báo lỗi
        if (!isFound) {
            System.out.println("Không tìm thấy mẫu xe " + modelNameFindout + " trong kho."); 
        }
    }

}