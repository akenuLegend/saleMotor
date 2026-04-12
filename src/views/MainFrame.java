package views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        // Cài đặt cơ bản cho cửa sổ
        setTitle("Phần Mềm Quản Lý Cửa Hàng Xe Máy");
        setSize(1000, 600); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình

        // Tạo JTabbedPane để chứa các tab
        JTabbedPane tabbedPane = new JTabbedPane();

        // Thêm các Panel (chưa có ruột) vào các Tab tương ứng với phác thảo của bạn
        tabbedPane.addTab("Nhập xe", new ImportMotorPanel());
        tabbedPane.addTab("KT tồn kho", new JPanel()); // Sẽ code sau
        tabbedPane.addTab("Bán hàng", new JPanel());   // Sẽ code sau
        tabbedPane.addTab("Bảo hành", new JPanel());   // Sẽ code sau
        tabbedPane.addTab("Customer", new JPanel());   // Sẽ code sau

        // Thêm tabbedPane vào Frame
        add(tabbedPane, BorderLayout.CENTER);
    }
}