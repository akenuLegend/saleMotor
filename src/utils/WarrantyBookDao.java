package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import data.JDBCUtil;
import models.WarrantyBook;

public class WarrantyBookDao implements DAOInterface<WarrantyBook> {

    private static WarrantyBookDao instance;

    public static WarrantyBookDao getInstance() {
        if (instance == null) {
            instance = new WarrantyBookDao();
        }
        return instance;
    }

    @Override
    public int insert(WarrantyBook t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Cột: vin, customer_id, issue_date, expiry_date
            String sql = "INSERT INTO warranty_books (vin, customer_id, issue_date, exp_date) VALUES (?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getVin());
            st.setInt(2, t.getCustomerId());
            // Chuyển LocalDate sang java.sql.Date
            st.setDate(3, Date.valueOf(t.getIssueDate()));
            st.setDate(4, Date.valueOf(t.getExpDate()));

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(WarrantyBook t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Xóa sổ bảo hành dựa trên số VIN (vì mỗi xe thường chỉ có 1 sổ)
            String sql = "DELETE FROM warranty_books WHERE vin = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getVin());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}