package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import data.JDBCUtil;
import models.Customer;

public class CustomerDao implements DAOInterface<Customer> {

    private static CustomerDao instance;

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    @Override
    public int insert(Customer t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO customers (id, identity_card, full_name, date_of_birth, phone, address, email) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            st.setString(2, t.getIdentityCard());
            st.setString(3, t.getFullName());
            // Chuyển đổi LocalDate sang sql.Date
            st.setDate(4, Date.valueOf(t.getDateOfBirth()));
            st.setString(5, t.getPhone());
            st.setString(6, t.getAddress());
            st.setString(7, t.getEmail());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(Customer t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM customers WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Customer selectById(int id) {
    Customer customer = null;
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // Đọc dữ liệu từ dòng tìm được và tạo đối tượng
            customer = new Customer(
                rs.getString("full_name"),
                rs.getString("phone"),
                rs.getInt("id"),
                rs.getString("identity_card"),
                rs.getDate("date_of_birth").toLocalDate(),
                rs.getString("address"),
                rs.getString("email")
            );
        }
        JDBCUtil.closeConnection(con);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return customer;
    }

    // Lấy danh sách và cập nhật lên database
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM customers ORDER BY id ASC";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = new Customer(
                    rs.getString("full_name"),
                    rs.getString("phone"),
                    rs.getInt("id"),
                    rs.getString("identity_card"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getString("address"),
                    rs.getString("email")
                );
                ketQua.add(c);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(Customer t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE customers SET identity_card=?, full_name=?, date_of_birth=?, phone=?, address=?, email=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getIdentityCard());
            st.setString(2, t.getFullName());
            st.setDate(3, java.sql.Date.valueOf(t.getDateOfBirth()));
            st.setString(4, t.getPhone());
            st.setString(5, t.getAddress());
            st.setString(6, t.getEmail());
            st.setInt(7, t.getId());
            
            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}