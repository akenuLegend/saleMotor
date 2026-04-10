// Buoc 1: Import cac class tu package models vao de su dung
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
    IN_STOCK, SOLD //cac gia tri co dinh cua kieu status
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
       
        
        
        // // gia su co db: cua hang:

        // MotorImportService kho1= new MotorImportService();

        // // 1. nhap xe vao kho

        // // nhap xe:
        // MotorbikeModel model1 = new MotorbikeModel(1, "Vision", "Honda", "Sport", "Xe tay ga pho thong, phu hop voi moi doi tuong khach hang.");
        // MotorbikeVersion version1 = new MotorbikeVersion("Xanh den", "125cc", "35 trieu", model1);
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
    //     System.out.println("   DEMO CHUONG TRINH QUAN LY CUA HANG BAN XE GAN MAY   ");
    //     System.out.println("======================================================\n");

    //     // ---------------------------------------------------------
    //     // 1. nhap hang & quan ly kho xe
    //     // ---------------------------------------------------------
    //     System.out.println("--- BUOC 1: NHAP XE VAO KHO ---");
    //     MotorImportService khoXe = new MotorImportService();

    //     // tao hoa don nhap hang
    //     PurchaseOrder po1 = new PurchaseOrder(1001, "Honda Vietnam", LocalDate.of(2026, 4, 1));
    //     khoXe.doPurchase(po1);

    //     // khoi tao danh muc xe (Model & Version)
    //     MotorbikeModel modelVision = new MotorbikeModel(1, "Vision", "Honda", "Tay ga", "Xe tay ga pho thong, phu hop voi moi doi tuong khach hang.");
    //     MotorbikeVersion versionVision = new MotorbikeVersion("Xanh den", "125cc", "35 trieu", modelVision);

    //     MotorbikeModel modelExciter = new MotorbikeModel(2, "Exciter 155", "Yamaha", "Con tay", "Xe con tay the thao");
    //     MotorbikeVersion versionExciter = new MotorbikeVersion("Xanh GP", "155cc", "50 trieu", modelExciter);

    //     // nhap cac chiec xe thuc te (Instance) vao kho
    //     MotorbikeInstance bike1 = new MotorbikeInstance("VIN-V01", "ENG-V001", versionVision, LocalDate.of(2026, 4, 1));
    //     MotorbikeInstance bike2 = new MotorbikeInstance("VIN-V02", "ENG-V002", versionVision, LocalDate.of(2026, 4, 1));
    //     MotorbikeInstance bike3 = new MotorbikeInstance("VIN-E01", "ENG-E001", versionExciter, LocalDate.of(2026, 4, 2));

    //     khoXe.importMotor(bike1);
    //     khoXe.importMotor(bike2);
    //     khoXe.importMotor(bike3);
        
    //     System.out.println("Da nhap kho 3 chiec xe.");
    //     System.out.println("Danh sach xe hien tai trong kho:");
    //     khoXe.showListMotor();


    //     // ---------------------------------------------------------
    //     // 2. KIEM TRA TON KHO THEO MAU XE
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BUOC 2: KIEM TRA TINH TRANG KHO ---");
    //     checkStatusWareHouse wareHouse = new checkStatusWareHouse();
    //     System.out.println("Tim xe Vision trong kho:");
    //     wareHouse.checkStatus("Vision", khoXe); // test ok

    //     System.out.println("Tim xe Exciter trong kho:");
    //     wareHouse.checkStatus("Exciter 155", khoXe); // test ok

    //     System.out.println("Tim xe Yamaha trong kho:");
    //     wareHouse.checkStatus("Yamaha", khoXe); // test truong hop ko co xe trong kho

    //     // ---------------------------------------------------------
    //     // 3. QUAN LY KHACH HANG
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BUOC 3: TIEP NHAN KHACH HANG MOI ---");
    //     CustomerService customerService = new CustomerService();
        
    //     Customer khachHang1 = new Customer("Dang Bao", "0901234567", 1, "079012345678", LocalDate.of(1990, 1, 1), "123 Nguyen Trai, HN", "dangbao@hehe.com");
    //     customerService.addCustomer(khachHang1);
    //     System.out.println("Da luu thong tin khach hang:");
    //     khachHang1.showInforCustomer();


    //     // ---------------------------------------------------------
    //     // 4. BAN HANG
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
        
    //     // Cach 1: In toan bo danh sach xe trong kho 
    //     //khoXe.showListMotor(); // TEST OK
        
    //     // Cach 2: loc rieng dong Vision 
    //     wareHouse.checkStatus("Vision", khoXe); //TEST OK


    //     // ---------------------------------------------------------
    //     // 5. BAO HANH
    //     // ---------------------------------------------------------
    //     System.out.println("\n--- BUOC 5: DANG KY VA GHI NHAN BAO HANH ---");
    //     // Cap so bao hanh 3 nam
    //     WarrantyBook warrantyBook = new WarrantyBook(bike1.getVin(), khachHang1.getId(), LocalDate.now(), LocalDate.now().plusYears(3));
        
    //     // Ghi nhan 1 lan khach mang xe den bao duong sau do
    //     WarrantyVisit visit1 = new WarrantyVisit(1, 1, LocalDate.now().plusMonths(3), 1500, "Bao duong dinh ky lan 1");
    //     visit1.setTechnicianNotes("Thay nhot may, kiem tra phanh, dong co hoat dong tot.");
        
    //     khachHang1.updateWarrantyVisit(visit1);
        
    //     System.out.println("Lich su bao duong cua xe " + bike1.getVin() + ":");
    //     khachHang1.showWarrantyHistory();

    //     System.out.println("\n======================================================");
    //     System.out.println("                    HOAN TAT DEMO                     ");
    //     System.out.println("======================================================");
    // }

    }
}