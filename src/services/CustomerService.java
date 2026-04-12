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
            c.showInforCustomer(); // Sử dụng hàm show thông tin đã viết trong Model
        } else {
            System.out.println("Customer not found!");
        }
        return c;
    }

    public void addNewSaleOrder(SaleOrder order) {
        if (SaleOrderDao.getInstance().insert(order) > 0) {
            System.out.println("Success: " + order.getId());
        } else {
            System.out.println("Error occurred while creating the sales order.");
        }
    }

    public void addSaleOrderDetail(SaleOrderDetail detail) {
        int checkDetail = SaleOrderDetailDao.getInstance().insert(detail);
        if (checkDetail > 0) {
            System.out.println("Saved detail for VIN: " + detail.getVin() + " successfully!");
        }
        else {
            System.out.println("Error occurred while saving order detail. Please check the order ID and VIN.");
        }
    }

    public void addNewWarrantyBook(WarrantyBook book) {
        if (WarrantyBookDao.getInstance().insert(book) > 0) {
            System.out.println("Success: Warranty book created for VIN: " + book.getVin());
        } else {
            System.out.println("Error: Failed to create warranty book. Please check if the VIN and Customer ID already exist.");
        }
    }

    public void addNewWarrantyVisit(WarrantyVisit visit) {
    if (WarrantyVisitDao.getInstance().insert(visit) > 0) {
        System.out.println("Success: Warranty visit recorded for VIN: " + visit.getVin());
    } else {
        System.out.println("Error: Failed to record warranty visit. Please check the warranty book ID.");
    }
    }

    public void showPurchaseHistory(int customerId) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT o.order_date, d.vin, d.sale_price " +
                         "FROM sale_orders o " +
                         "JOIN sale_order_details d ON o.order_id = d.order_id " +
                         "WHERE o.customer_id = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();

            System.out.println("--- Purchase History (Customer ID: " + customerId + ") ---");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("Purchase Date: " + rs.getDate("order_date") + 
                                   " | VIN: " + rs.getString("vin") + 
                                   " | Sale Price: " + rs.getInt("sale_price"));
            }
            if (!hasData) System.out.println("This customer has not purchased any vehicles.");
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWarrantyHistory(int customerId) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT v.visit_date, v.vin, v.technician_notes " +
                        "FROM warranty_visits v " +
                        "JOIN warranty_books b ON v.vin = b.vin " +
                        "WHERE b.customer_id = ? ORDER BY v.visit_date DESC";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();

            System.out.println("--- Warranty History (Customer ID: " + customerId + ") ---");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("Date: " + rs.getDate("visit_date") + 
                                " | Vehicle VIN: " + rs.getString("vin") + 
                                " | Technician Notes: " + rs.getString("technician_notes"));
            }
            if (!hasData) System.out.println("No warranty history found.");
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
                        "JOIN sale_order_details d ON o.order_id = d.order_id " +
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