package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import data.JDBCUtil;
import models.PurchaseOrder;

public class PurchaseOrderDao implements DAOInterface<PurchaseOrder> {

    private static PurchaseOrderDao instance;

    public static PurchaseOrderDao getInstance() {
        if (instance == null) {
            instance = new PurchaseOrderDao();
        }
        return instance;
    }

    @Override
    public int insert(PurchaseOrder t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Cột: id, supplier, order_date, status
            String sql = "INSERT INTO purchase_orders (id, supplier, order_date, status) VALUES (?, ?, ?, 'IN_STOCK')";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getid());
            st.setString(2, t.getsupplier());
            // Chuyển LocalDate sang sql.Date
            st.setDate(3, Date.valueOf(t.getorderDate()));

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(PurchaseOrder t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM purchase_orders WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getid());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}