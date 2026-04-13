package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import data.JDBCUtil;
import models.SaleOrder;

public class SaleOrderDao implements DAOInterface<SaleOrder> {

    private static SaleOrderDao instance;

    public static SaleOrderDao getInstance() {
        if (instance == null) {
            instance = new SaleOrderDao();
        }
        return instance;
    }

    @Override
    public int insert(SaleOrder t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
       
            String sql = "INSERT INTO sale_orders (order_id, customer_id, order_date, payment_status) VALUES (?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            st.setInt(2, t.getCustomerId());
            st.setDate(3, Date.valueOf(t.getOrderDate()));
            st.setString(4, t.getPaymentStatus());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(SaleOrder t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM sale_orders WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}