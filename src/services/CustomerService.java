package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.JDBCUtil;
import models.Customer;
import models.SaleOrder;
import models.SaleOrderDetail;
import models.WarrantyBook;
import models.WarrantyVisit;
import utils.CustomerDao;
import utils.SaleOrderDao;
import utils.SaleOrderDetailDao;
import utils.WarrantyBookDao;
import utils.WarrantyVisitDao;

public class CustomerService {

    public void addNewCustomer(Customer customer) {
        if (CustomerDao.getInstance().insert(customer) > 0) {
            System.out.println("success add");
        } else {
            System.out.println("customer already exists!");
        }
    }

    public void deleteCustomer(Customer customer) {
        if (CustomerDao.getInstance().delete(customer) > 0) {
            System.out.println("success delete");
        } else {
            System.out.println("customer not found!");
        }
    }

    public Customer searchCustomerById(int id) {
        Customer c = CustomerDao.getInstance().selectById(id);
        if (c != null) {
            System.out.println("Customer found:");
            c.showInforCustomer(); // Sử dụng hàm show thông tin cậu đã viết trong Model
        } else {
            System.out.println("Customer not found!");
        }
        return c;
    }

    public void addNewSaleOrder(SaleOrder order) {
        if (SaleOrderDao.getInstance().insert(order) > 0) {
            System.out.println("Tạo đơn hàng thành công! ID: " + order.getId());
        } else {
            System.out.println("Lỗi khi tạo đơn hàng trên hệ thống.");
        }
    }

    public void addSaleOrderDetail(SaleOrderDetail detail) {
        int checkDetail = SaleOrderDetailDao.getInstance().insert(detail);
        if (checkDetail > 0) {
            System.out.println("Lưu chi tiết đơn hàng cho xe " + detail.getVin() + " thành công!");
            
        }
        else {
            System.out.println("Lỗi khi lưu chi tiết đơn hàng. Kiểm tra lại mã đơn hàng và VIN xe!");
        }
    }

    public void addNewWarrantyBook(WarrantyBook book) {
        if (WarrantyBookDao.getInstance().insert(book) > 0) {
            System.out.println("Đã tạo sổ bảo hành cho xe VIN: " + book.getVin());
        } else {
            System.out.println("Lỗi: Không thể tạo sổ bảo hành. Kiểm tra xem VIN và Customer ID đã tồn tại chưa!");
        }
    }

    public void addNewWarrantyVisit(WarrantyVisit visit) {
    if (WarrantyVisitDao.getInstance().insert(visit) > 0) {
        System.out.println("Ghi nhận lịch sử bảo hành ID: " + visit.getId() + " thành công!");
    } else {
        System.out.println("Lỗi: Không thể ghi nhận lịch sử bảo hành. Kiểm tra lại mã sổ bảo hành!");
    }
    }

    public void showPurchaseHistory(int customerId) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT o.order_date, d.vin, d.sale_price " +
                        "FROM sale_orders o " +
                        "JOIN sale_order_details d ON o.id = d.order_id " +
                        "WHERE o.customer_id = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();

            System.out.println("--- LỊCH SỬ MUA HÀNG (Khách hàng ID: " + customerId + ") ---");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("Ngày mua: " + rs.getDate("order_date") + 
                                " | VIN: " + rs.getString("vin") + 
                                " | Giá bán: " + rs.getInt("sale_price"));
            }
            if (!hasData) System.out.println("Khách hàng này chưa mua xe nào.");
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWarrantyHistory(int customerId) {
        try {
            Connection con = JDBCUtil.getConnection();
            // Tìm các lượt visit dựa trên khách hàng sở hữu xe
            String sql = "SELECT v.visit_date, v.vin, v.technician_notes " +
                        "FROM warranty_visits v " +
                        "JOIN warranty_books b ON v.vin = b.vin " +
                        "WHERE b.customer_id = ? ORDER BY v.visit_date DESC";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();

            System.out.println("--- LỊCH SỬ BẢO HÀNH (Khách hàng ID: " + customerId + ") ---");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("Ngày: " + rs.getDate("visit_date") + 
                                " | Xe VIN: " + rs.getString("vin") + 
                                " | Ghi chú: " + rs.getString("technician_notes"));
            }
            if (!hasData) System.out.println("Chưa có lịch sử bảo hành.");
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getVehicleCount_v2(int customerId) {
        int count = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM sale_orders o " +
                        "JOIN sale_order_details d ON o.id = d.order_id " +
                        "WHERE o.customer_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}