package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import data.JDBCUtil;
import models.MotorbikeInstance;
import models.MotorbikeModel;
import models.MotorbikeVersion;

public class MotorbikeInstanceDao implements DAOInterface<MotorbikeInstance> {

    private static MotorbikeInstanceDao instance;

    public static MotorbikeInstanceDao getInstance() {
        if (instance == null) {
            instance = new MotorbikeInstanceDao();
        }
        return instance;
    }

    @Override
    public int insert(MotorbikeInstance t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO motorbike_instances (version_id, vin, engine_number, status, import_date) VALUES (?, ?, ?, 'IN_STOCK', ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getVersion().getVersionID());
            st.setString(2, t.getVin());
            st.setString(3, t.getEngineNumber());

            st.setDate(4, Date.valueOf(t.getImportDate()));

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(MotorbikeInstance t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM motorbike_instances WHERE vin = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getVin());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }


    public ArrayList<MotorbikeInstance> selectAll() {
    ArrayList<MotorbikeInstance> list = new ArrayList<>();
    try {
        Connection con = JDBCUtil.getConnection();

        String sql = "SELECT i.*, v.color, v.phan_khoi, v.price, m.model_name, m.brand, m.model_id " +
                     "FROM motorbike_instances i " +
                     "JOIN motorbike_versions v ON i.version_id = v.version_id " +
                     "JOIN motorbike_models m ON v.model_id = m.model_id";
        
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            // 1. Tạo Model
            MotorbikeModel model = new MotorbikeModel(
                rs.getInt("model_id"),
                rs.getString("model_name"),
                rs.getString("brand"),
                "", "" // Các trường khác tạm để trống nếu không cần show
            );

            // 2. Tạo Version
            MotorbikeVersion version = new MotorbikeVersion(
                rs.getInt("version_id"),
                rs.getString("color"),
                rs.getString("engine_capacity"),
                rs.getString("price"),
                model
            );

            // 3. Tạo Instance và thêm vào list
            MotorbikeInstance instance = new MotorbikeInstance(
                rs.getString("vin"),
                rs.getString("engine_number"),
                version,
                rs.getDate("import_date").toLocalDate()
            );
            list.add(instance);
        }
        JDBCUtil.closeConnection(con);
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }


    public ArrayList<MotorbikeInstance> selectByModelName(String modelName) {
    ArrayList<MotorbikeInstance> list = new ArrayList<>();
    try {
        Connection con = JDBCUtil.getConnection();

        String sql = "SELECT i.*, v.color, v.engine_capacity, v.price, m.model_name, m.brand, m.model_id " +
                     "FROM motorbike_instances i " +
                     "JOIN motorbike_versions v ON i.version_id = v.version_id " +
                     "JOIN motorbike_models m ON v.model_id = m.model_id " +
                     "WHERE m.model_name = ? AND i.status = 'IN_STOCK'";
        
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, modelName);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            MotorbikeModel model = new MotorbikeModel(
                rs.getInt("model_id"), rs.getString("model_name"),
                rs.getString("brand"), "", ""
            );
            MotorbikeVersion version = new MotorbikeVersion(
                rs.getInt("version_id"),
                rs.getString("color"), rs.getString("engine_capacity"),
                rs.getString("price"), model
            );
            MotorbikeInstance instance = new MotorbikeInstance(
                rs.getString("vin"), rs.getString("engine_number"),
                version, rs.getDate("import_date").toLocalDate()
            );
            list.add(instance);
        }
        JDBCUtil.closeConnection(con);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }

    public void updateStatus(MotorbikeInstance t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE motorbike_instances SET status = 'SOLD' WHERE vin = ?";
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getVin());

            st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}