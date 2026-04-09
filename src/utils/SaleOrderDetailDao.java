package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import data.JDBCUtil;
import models.SaleOrderDetail;

public class SaleOrderDetailDao implements DAOInterface<SaleOrderDetail> {

    private static SaleOrderDetailDao instance;

    public static SaleOrderDetailDao getInstance() {
        if (instance == null) {
            instance = new SaleOrderDetailDao();
        }
        return instance;
    }

    @Override
    public int insert(SaleOrderDetail t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO sale_order_details (order_id, vin, sale_price) VALUES (?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            // Lấy order_id từ class cha SaleOrder (thông qua hàm getId)
            st.setInt(1, t.getId()); 
            st.setString(2, t.getVin());
            st.setInt(3, t.getSalePrice());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(SaleOrderDetail t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Xóa dựa trên VIN vì mỗi xe chỉ bán 1 lần (là duy nhất)
            String sql = "DELETE FROM sale_order_details WHERE vin = ?";
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