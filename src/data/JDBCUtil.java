package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;
        try {
            // 1. Đăng ký driver (Với bản mới có thể bỏ qua nhưng viết vào cho chắc)
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            // 2. Các thông số kết nối
            String url = "jdbc:postgresql://localhost:5432/MotobikeDB";
            String user = "postgres"; // Tên user mặc định của cậu
            String pass = "1"; // Thay bằng mật khẩu lúc cậu cài Postgres
            
            // 3. Tạo kết nối
            c = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}