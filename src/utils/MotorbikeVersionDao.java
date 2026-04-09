package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import data.JDBCUtil;
import models.MotorbikeVersion;

public class MotorbikeVersionDao implements DAOInterface<MotorbikeVersion> {

    private static MotorbikeVersionDao instance;

    public static MotorbikeVersionDao getInstance() {
        if (instance == null) {
            instance = new MotorbikeVersionDao();
        }
        return instance;
    }

    @Override
    public int insert(MotorbikeVersion t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Chú ý: Cột model_id lấy từ t.getmodel().getmodelId()
            String sql = "INSERT INTO motorbike_versions (model_id, color, engine_capacity, price) VALUES (?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getmodel().getmodelId());
            st.setString(2, t.getcolor());
            st.setString(3, t.getphankhoi());
            st.setString(4, t.getprice());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(MotorbikeVersion t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Xóa dựa trên model_id và màu sắc (vì cậu không có ID phiên bản riêng)
            String sql = "DELETE FROM motorbike_versions WHERE model_id = ? AND color = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getmodel().getmodelId());
            st.setString(2, t.getcolor());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }


}