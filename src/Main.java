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
       
        
        JDBCUtil.closeConnection(connection);


        
        // // giả sử có db: cua hang:

        // MotorImportService kho1= new MotorImportService();

        // // 1. nhap xe vao kho

        // // nhap xe:
        // MotorbikeModel model1 = new MotorbikeModel(1, "Vision", "Honda", "Sport", "Xe tay ga phổ thông, phù hợp với mọi đối tượng khách hàng.");
        // MotorbikeVersion version1 = new MotorbikeVersion("Xanh đen", "125cc", "35 triệu", model1);
        // MotorbikeInstance bike1 = new MotorbikeInstance("VIN-V01", "ENG-V001", version1, java.time.LocalDate.of(2026, 4, 1));

        // kho1.importMotor(bike1);

        // // hoa don nhap xe:
        // PurchaseOrder po1 = new PurchaseOrder(1001, "Honda Vietnam", java.time.LocalDate.of(2026, 4, 1));
        // kho1.doPurchase(po1);

        // // 2. kiem tra ton kho
        // checkStatusWareHouse wareHouse = new checkStatusWareHouse();
        // wareHouse.checkStatus("Vision", kho1);

        // // 5. tao khach hang

        // Customer DangBao = new Customer("Dang Bao", "0901234567", 1, "079012345678", LocalDate.of(1990, 1, 1), "123 Nguyen Trai, HN", "dangbao@hehe.com");
        // // quan ly khach hang
        // CustomerService customerService = new CustomerService();
        // customerService.addCustomer(DangBao);

        // // 3. ban xe cho Dang Bao
        // SaleOrderDetail sale1 = new SaleOrderDetail(1, "VIN-V01", 36000000);
        // bike1.setStatusSold();
        // DangBao.updatePurchaseHistory(sale1);
        // WarrantyBook warranty1 = new WarrantyBook("VIN-V01", 1, LocalDate.of(2026, 4, 4), LocalDate.of(2029, 4, 4));


        // // 4. bao hanh xe cho Dang Bao

    //     System.out.println("======================================================");
    //     System.out.println("   DEMO CHƯƠNG TRÌNH QUẢN LÝ CỬA HÀNG BÁN XE GẮN MÁY   ");
    //     System.out.println("======================================================\n");

    //     // ---------------------------------------------------------
    //     // 1. nhập hàng & quản lý kho xe
    //     // ---------------------------------------------------------
    //     System.out.println("--- BƯỚC 1: NHẬP XE VÀO KHO ---");
    //     MotorImportService khoXe = new MotorImportService();

    //     // tạo hóa đơn nhập hàng
    //     PurchaseOrder po1 = new PurchaseOrder(1001, "Honda Vietnam", LocalDate.of(2026, 4, 1));
    //     khoXe.doPurchase(po1);

    //     // khởi tạo danh mục xe (Model & Version)
    //     MotorbikeModel modelVision = new MotorbikeModel(1, "Vision", "Honda", "Tay ga", "Xe tay ga phổ thông, phù hợp với mọi đối tượng khách hàng.");
    //     MotorbikeVersion versionVision = new MotorbikeVersion("Xanh đen", "125cc", "35 triệu", modelVision);

    //     MotorbikeModel modelExciter = new MotorbikeModel(2, "Exciter 155", "Yamaha", "Côn tay", "Xe côn tay thể thao");
    //     MotorbikeVersion versionExciter = new MotorbikeVersion("Xanh GP", "155cc", "50 triệu", modelExciter);

    //     // nhập các chiếc xe thực tế (Instance) vào kho
    //     MotorbikeInstance bike1 = new MotorbikeInstance("VIN-V01", "ENG-V001", versionVision, LocalDate.of(2026, 4, 1));
    //     MotorbikeInstance bike2 = new MotorbikeInstance("VIN-V02", "ENG-V002", versionVision, LocalDate.of(2026, 4, 1));
    //     MotorbikeInstance bike3 = new MotorbikeInstance("VIN-E01", "ENG-E001", versionExciter, LocalDate.of(2026, 4, 2));

    //     khoXe.importMotor(bike1);
    //     khoXe.importMotor(bike2);
    //     khoXe.importMotor(bike3);
        
    //     System.out.println("Đã nhập kho 3 chiếc xe.");
    //     System.out.println("Danh sách xe hiện tại trong kho:");
    //     khoXe.showListMotor();


    //     // ---------------------------------------------------------
    //     // 2. KIỂM TRA TỒN KHO THEO MẪU XE
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BƯỚC 2: KIỂM TRA TÌNH TRẠNG KHO ---");
    //     checkStatusWareHouse wareHouse = new checkStatusWareHouse();
    //     System.out.println("Tìm xe Vision trong kho:");
    //     wareHouse.checkStatus("Vision", khoXe); // test ok

    //     System.out.println("Tìm xe Exciter trong kho:");
    //     wareHouse.checkStatus("Exciter 155", khoXe); // test ok

    //     System.out.println("Tìm xe Yamaha trong kho:");
    //     wareHouse.checkStatus("Yamaha", khoXe); // test trường hợp ko có xe trong kho

    //     // ---------------------------------------------------------
    //     // 3. QUẢN LÝ KHÁCH HÀNG
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BƯỚC 3: TIẾP NHẬN KHÁCH HÀNG MỚI ---");
    //     CustomerService customerService = new CustomerService();
        
    //     Customer khachHang1 = new Customer("Đăng Bảo", "0901234567", 1, "079012345678", LocalDate.of(1990, 1, 1), "123 Nguyen Trai, HN", "dangbao@hehe.com");
    //     customerService.addCustomer(khachHang1);
    //     System.out.println("Đã lưu thông tin khách hàng:");
    //     khachHang1.showInforCustomer();


    //     // ---------------------------------------------------------
    //     // 4. BÁN HÀNG
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BUOC 4: XU LY GIAO DICH BAN XE ---");
    //     System.out.println("Khach hang quyet dinh mua 1 chiec Vision Xanh Den (Ma VIN: " + bike1.getVin() + ").");
        
    //     // Cap nhat trang thai chiec xe bike1 thanh SOLD (Da ban)
    //     bike1.setStatusSold();
        
    //     // Tao don hang chi tiet va luu vao lich su mua hang cua khach
    //     SaleOrderDetail sale1 = new SaleOrderDetail(khachHang1.getId(), bike1.getVin(), 36000000);
    //     khachHang1.updateVehicleCount(); // Tang so luong xe da mua
    //     khachHang1.updatePurchaseHistory(sale1);
        
    //     System.out.println("-> Giao dich thanh cong! Trang thai xe " + bike1.getVin() + " hien tai: " + bike1.getStatus());


    //     // ---------------------------------------------------------
    //     // 4.1. KIEM TRA LAI KHO SAU KHI BAN 
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BUOC 4.1: KIEM TRA LAI KHO SAU KHI BAN ---");
    //     System.out.println("Kiem tra xem chiec Vision Xanh Den con lai (VIN-V02) co bi anh huong khong:");
        
    //     // Cách 1: In toàn bộ danh sách xe trong kho 
    //     //khoXe.showListMotor(); // TEST OK
        
    //     // Cách 2: lọc riêng dòng Vision 
    //     wareHouse.checkStatus("Vision", khoXe); //TEST OK


    //     // ---------------------------------------------------------
    //     // 5. BẢO HÀNH
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BƯỚC 5: ĐĂNG KÝ VÀ GHI NHẬN BẢO HÀNH ---");
    //     // Cấp sổ bảo hành 3 năm
    //     WarrantyBook warrantyBook = new WarrantyBook(bike1.getVin(), khachHang1.getId(), LocalDate.now(), LocalDate.now().plusYears(3));
        
    //     // Ghi nhận 1 lần khách mang xe đến bảo dưỡng sau đó
    //     WarrantyVisit visit1 = new WarrantyVisit(1, 1, LocalDate.now().plusMonths(3), 1500, "Bảo dưỡng định kỳ lần 1");
    //     visit1.setTechnicianNotes("Thay nhớt máy, kiểm tra phanh, động cơ hoạt động tốt.");
        
    //     khachHang1.updateWarrantyVisit(visit1);
        
    //     System.out.println("Lịch sử bảo dưỡng của xe " + bike1.getVin() + ":");
    //     khachHang1.showWarrantyHistory();

    //     System.out.println("\n======================================================");
    //     System.out.println("                    HOÀN TẤT DEMO                     ");
    //     System.out.println("======================================================");
    // }

    }
}