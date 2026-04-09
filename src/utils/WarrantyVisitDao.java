package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import data.JDBCUtil;
import models.WarrantyVisit;

public class WarrantyVisitDao implements DAOInterface<WarrantyVisit> {

    private static WarrantyVisitDao instance;

    public static WarrantyVisitDao getInstance() {
        if (instance == null) {
            instance = new WarrantyVisitDao();
        }
        return instance;
    }

    @Override
    public int insert(WarrantyVisit t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            // Cột: id, vin, visit_date, km_reading, technician_notes
            String sql = "INSERT INTO warranty_visits (id, vin, visit_date, km_reading, technician_notes) "
                       + "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            st.setString(2, t.getVin()); // Dùng VIN làm tham chiếu trực tiếp
            st.setDate(3, Date.valueOf(t.getVisitDate()));
            st.setInt(4, t.getKmReading());
            st.setString(5, t.getTechnicianNotes());

            ketQua = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(WarrantyVisit t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM warranty_visits WHERE id = ?";
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