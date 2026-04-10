package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import data.JDBCUtil;
import models.MotorbikeModel;


public class MotorbikeModelDao implements DAOInterface<MotorbikeModel> {

    @Override
    public int insert(MotorbikeModel t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO motorbike_models (model_id, model_name, brand, type, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getmodelId());
            st.setString(2, t.getmodelname());
            st.setString(3, t.getbrand());
            st.setString(4, t.gettype());
            st.setString(5, t.getdescription());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(MotorbikeModel t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM motorbike_models WHERE model_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getmodelId());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }


    private static MotorbikeModelDao instance;

    public static MotorbikeModelDao getInstance() {
        if (instance == null) {
            instance = new MotorbikeModelDao();
        }
        return instance;
    }
}