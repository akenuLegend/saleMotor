// Bước 1: Import các class từ package models vào để sử dụng
import models.MotorbikeInstance;
import models.MotorbikeModel;
import models.MotorbikeVersion;
import models.PurchaseOrder;
import models.SaleOrder;
import models.SaleOrderDetail;
import models.WarrantyBook;
import models.WarrantyVisit;
import services.CustomerService;
import services.MotorImportService;
import services.checkStatusWareHouse;
import utils.MotorbikeInstanceDao;
import utils.MotorbikeModelDao;
import utils.MotorbikeVersionDao;
import utils.SaleOrderDao;
import models.Customer;
import models.WarrantyVisit;
import java.time.LocalDate;
import data.JDBCUtil;
import java.sql.Connection;



enum Status {
    IN_STOCK, SOLD //các giá trị cố định của kiểu status
}

// java.time.localDate
public class Main {
    public static void main(String[] args) {


        Connection connection = JDBCUtil.getConnection();
        
        if(connection != null) {
            System.out.println("welcome");
        } else {
            System.out.println("failed");
        }


        //  hoat dong o day
        // work flow 
        
        MotorImportService importService = new MotorImportService();
        CustomerService customerService = new CustomerService();

        


                //4. Thêm purchase order (Hóa đơn nhập hàng)
        System.out.println("--- 4. TẠO HÓA ĐƠN NHẬP ---");
        PurchaseOrder po = new PurchaseOrder(101, "Honda Vietnam Factory", java.time.LocalDate.of(2026, 4, 1));
        importService.addPurchaseOrder(po);
        PurchaseOrder po1 = new PurchaseOrder(102, "Yamaha Japan Factory", java.time.LocalDate.of(2026,4,2));
        importService.addPurchaseOrder(po1);

        System.out.println("--- 1. THÊM MODEL ---");

        MotorbikeModel model1 = new MotorbikeModel(1, "Air Blade", "Honda", "Sport", "Xe loai the thao"); 
        MotorbikeModel model2 = new MotorbikeModel(2, "Vision", "Honda", "Pho thong", "Xe loai pho thong");
        MotorbikeModel model3 = new MotorbikeModel(3, "SH", "Honda", "Cao cap", "Xe loai cao cap");
        MotorbikeModel model4 = new MotorbikeModel(4, "Sirius", "Yamaha", "Xe so", "Xe pho thong gia re");
        MotorbikeModelDao.getInstance().insert(model1);
        MotorbikeModelDao.getInstance().insert(model2);
        MotorbikeModelDao.getInstance().insert(model3);
        MotorbikeModelDao.getInstance().insert(model4);

        // 2. Thêm 4 version (2 cho model 1, 2 cho model 2)
        System.out.println("--- 2. THÊM VERSION ---");
        // Giả sử constructor: (version_id, model_id, color, price)
        MotorbikeVersion v1_m1 = new MotorbikeVersion(1001,"red", "130cc", "35000000", model1);
        MotorbikeVersion v2_m1 = new MotorbikeVersion(1002, "white", "130cc", "46000000", model1);
        MotorbikeVersion v1_m2 = new MotorbikeVersion(2001, "blue", "170cc", "70000000", model2);
        MotorbikeVersion v2_m2 = new MotorbikeVersion(2002, "black", "210cc", "56000000", model2);
        MotorbikeVersion v1_m3 = new MotorbikeVersion(3001, "white", "150cc", "110000000", model3);
        MotorbikeVersion v2_m3 = new MotorbikeVersion(3002, "brown", "150cc", "105000000", model3);
        MotorbikeVersion v1_m4 = new MotorbikeVersion(4001, "white", "110cc", "23000000", model4);
        MotorbikeVersion v2_m4 = new MotorbikeVersion(4002, "black", "110cc", "22000000", model4);

        MotorbikeVersionDao.getInstance().insert(v1_m1);
        MotorbikeVersionDao.getInstance().insert(v2_m1);
        MotorbikeVersionDao.getInstance().insert(v1_m2);
        MotorbikeVersionDao.getInstance().insert(v2_m2);
        MotorbikeVersionDao.getInstance().insert(v1_m3);
        MotorbikeVersionDao.getInstance().insert(v2_m3);
        MotorbikeVersionDao.getInstance().insert(v1_m4);
        MotorbikeVersionDao.getInstance().insert(v2_m4);
// 3. Thêm 2 xe vào motorbike_instances
        System.out.println("--- 3. NHẬP XE VÀO KHO ---");
        // Giả sử constructor: (vin, engine_number, version_id, status)
        MotorbikeInstance bike1 = new MotorbikeInstance("VIN-AB-001", "ENG-001", v2_m1,java.time.LocalDate.of(2026, 4, 1));
        MotorbikeInstance bike2 = new MotorbikeInstance("VIN-VS-002", "ENG-002", v1_m2,java.time.LocalDate.of(2026, 4, 1));
        MotorbikeInstance bike3 = new MotorbikeInstance("VIN-SH-001", "ESH-095", v1_m3,java.time.LocalDate.of(2026, 4, 2));
        MotorbikeInstance bike4 = new MotorbikeInstance("VIN-SI-001", "ESH-011", v2_m4,java.time.LocalDate.of(2026, 4, 2));

        // Gọi qua Service như cậu đã nhắc tớ trước đó
        importService.addNewMotorbike(bike1); 
        importService.addNewMotorbike(bike2);
        importService.addNewMotorbike(bike3);
        importService.addNewMotorbike(bike4);

        // 4. Kiem tra ton kho
        System.out.println("--- 4. KIEM TRA TON KHO ---");
        checkStatusWareHouse wareHouseService = new checkStatusWareHouse();
        wareHouseService.checkStatus("Vision"); // Kiem tra truc tiep bang ten model
        wareHouseService.checkStatus("Air Blade");
        wareHouseService.checkStatus("SH");
        wareHouseService.checkStatus("Sirius");
        wareHouseService.checkStatus("Exciter"); // Khong co trong kho

        // 5. Tạo 2 customer, lưu vào DB
        System.out.println("--- 5. THÊM KHÁCH HÀNG ---");
        Customer cus = new Customer("Trần Văn A", "0909123456", 210, "079012345678", java.time.LocalDate.of(1990, 1, 1), "123 Nguyen Trai, HN", "trannv@example.com"); // ID là 99
        customerService.addNewCustomer(cus); 
        Customer khachHang1 = new Customer("Dang Bao", "0901234567", 222, "0516653126516", LocalDate.of(1990, 1, 1), "123 Nguyen Trai, HN", "dangbao@test.com");
        customerService.addNewCustomer(khachHang1);

        // 6. Tạo sale_orders, sale_order_details và warranty_books cho khách hàng đó
        System.out.println("--- 6. BÁN XE VÀ TẠO SỔ BẢO HÀNH ---");
        // 6.1 Tạo hóa đơn tổng
        SaleOrder order = new SaleOrder(1, 210, java.time.LocalDate.of(2026, 4, 6)); // 
        SaleOrderDao.getInstance().insert(order);
        MotorbikeInstanceDao.getInstance().updateStatus(bike1); // Cập nhật trạng thái xe đã bán

        SaleOrder order1 = new SaleOrder(2, 222, java.time.LocalDate.of(2026, 4, 7)); // 
        SaleOrderDao.getInstance().insert(order1);
        MotorbikeInstanceDao.getInstance().updateStatus(bike2); // Cập nhật trạng thái xe đã bán


        // 6.2 Tạo chi tiết hóa đơn (Khách mua chiếc Air Blade VIN-AB-001 + Vision )
        // Nếu detail_id là SERIAL trong DB, không cần truyền detail_id ở đây
        SaleOrderDetail detail = new SaleOrderDetail(1, 210, "VIN-AB-001", 60000000); 
        customerService.addSaleOrderDetail(detail); 
        SaleOrderDetail detail1 = new SaleOrderDetail(2, 222, "VIN-VS-002", 70000000); 
        customerService.addSaleOrderDetail(detail1); 

        // 6.3 Tạo sổ bảo hành cho chiếc xe vừa bán
        WarrantyBook book = new WarrantyBook("VIN-AB-001", 210, java.time.LocalDate.now(), java.time.LocalDate.now().plusYears(2));
        customerService.addNewWarrantyBook(book);
        WarrantyBook book1 = new WarrantyBook("VIN-VS-002", 222, java.time.LocalDate.now(), java.time.LocalDate.now().plusYears(2));
        customerService.addNewWarrantyBook(book1);

        // 7. Tạo lịch sử bảo hành (WarrantyVisit)
        System.out.println("--- 7. GHI NHẬN LỊCH SỬ BẢO HÀNH ---");
        // Cấu trúc bảng: id, vin, visit_date, km_reading, technician_notes
        WarrantyVisit visit = new WarrantyVisit(1001, "VIN-AB-001", LocalDate.now().plusMonths(3), 1500, WarrantyVisit.error[0] + " - Thay nhớt miễn phí lần 1", 210);
        customerService.addNewWarrantyVisit(visit);

        WarrantyVisit visit1 = new WarrantyVisit(1002, "VIN-AB-001", LocalDate.now().plusMonths(4), 3000, WarrantyVisit.error[2] + " - Thay thắng miễn phí lần 1", 210);
        customerService.addNewWarrantyVisit(visit1);
        // ---------------------------------------------// test themx`


        // xem lịch sử bảo hành
        customerService.showWarrantyHistory(210);
        customerService.showPurchaseHistory(222);
        int vehicleCount = customerService.getVehicleCount_v2(222);
        System.out.println("Số lượng xe đã mua (Khách hàng ID: 222): " + vehicleCount);

        // xoa customer

        
        JDBCUtil.closeConnection(connection);


    }
}