package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import models.MotorbikeInstance;
import models.MotorbikeModel;
import models.MotorbikeVersion;
import models.PurchaseOrder;
import services.MotorImportService;

public class ImportMotorPanel extends JPanel {

    // Khai báo các text field
    private JTextField txtVIN, txtEngine, txtNhaCungCap, txtNgayNhap, txtOrderId;
    private JTextField txtVersionId, txtColor, txtCapacity, txtPrice;
    private JTextField txtModelId, txtBrand, txtType, txtName, txtDesc;
    private JButton btnNhap;

    public ImportMotorPanel() {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- CỘT TRÁI: Nhập VIN, Engine, Đơn hàng ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Form VIN/Engine
        JPanel formTraiTren = new JPanel(new GridLayout(2, 2, 10, 10));
        formTraiTren.add(new JLabel("Nhập VIN:"));
        txtVIN = new JTextField(); formTraiTren.add(txtVIN);
        formTraiTren.add(new JLabel("Nhập Engine Number:"));
        txtEngine = new JTextField(); formTraiTren.add(txtEngine);
        leftPanel.add(formTraiTren);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(new JSeparator());
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form Đơn Nhập Hàng (Thêm ID đơn hàng ở đây)
        leftPanel.add(new JLabel("THÔNG TIN ĐƠN NHẬP:"));
        JPanel formTraiDuoi = new JPanel(new GridLayout(3, 2, 10, 10));
        
        formTraiDuoi.add(new JLabel("Mã Đơn Nhập (ID):"));
        txtOrderId = new JTextField(); formTraiDuoi.add(txtOrderId);
        
        formTraiDuoi.add(new JLabel("Nhà cung cấp:"));
        txtNhaCungCap = new JTextField(); formTraiDuoi.add(txtNhaCungCap);
        
        formTraiDuoi.add(new JLabel("Ngày nhập (YYYY-MM-DD):"));
        txtNgayNhap = new JTextField(LocalDate.now().toString()); // Mặc định là ngày hôm nay
        formTraiDuoi.add(txtNgayNhap);
        
        leftPanel.add(formTraiDuoi);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        btnNhap = new JButton("NHẬP KHO HỆ THỐNG");
        btnNhap.setFont(new Font("Arial", Font.BOLD, 14));
        btnNhap.setBackground(new Color(0, 153, 76));
        btnNhap.setForeground(Color.WHITE);
        leftPanel.add(btnNhap);

        // --- CỘT PHẢI: Version và Model ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Form Version
        rightPanel.add(new JLabel("VERSION:"));
        JPanel formVersion = new JPanel(new GridLayout(3, 4, 10, 10));
        formVersion.add(new JLabel("Id:")); txtVersionId = new JTextField(); formVersion.add(txtVersionId);
        formVersion.add(new JLabel("Color:")); txtColor = new JTextField(); formVersion.add(txtColor);
        formVersion.add(new JLabel("Dung tích:")); txtCapacity = new JTextField(); formVersion.add(txtCapacity);
        formVersion.add(new JLabel("")); formVersion.add(new JLabel(""));
        formVersion.add(new JLabel("Giá bán:")); txtPrice = new JTextField(); formVersion.add(txtPrice);
        rightPanel.add(formVersion);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(new JSeparator());
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form Model
        rightPanel.add(new JLabel("MODEL:"));
        JPanel formModel = new JPanel(new GridLayout(3, 4, 10, 10));
        formModel.add(new JLabel("Id:")); txtModelId = new JTextField(); formModel.add(txtModelId);
        formModel.add(new JLabel("Hãng:")); txtBrand = new JTextField(); formModel.add(txtBrand);
        formModel.add(new JLabel("Tên xe:")); txtName = new JTextField(); formModel.add(txtName);
        formModel.add(new JLabel("Loại:")); txtType = new JTextField(); formModel.add(txtType);
        formModel.add(new JLabel("Mô tả:")); txtDesc = new JTextField(); formModel.add(txtDesc);
        rightPanel.add(formModel);

        add(leftPanel);
        add(rightPanel);

        setupListeners();
    }

    private void setupListeners() {
        btnNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. LẤY DỮ LIỆU ĐƠN HÀNG & NGÀY THÁNG
                    int orderId = Integer.parseInt(txtOrderId.getText());
                    String supplier = txtNhaCungCap.getText();
                    String dateString = txtNgayNhap.getText();
                    LocalDate importDate = LocalDate.parse(dateString); // Phải nhập đúng định dạng YYYY-MM-DD

                    // 2. LẤY DỮ LIỆU XE
                    int modelId = Integer.parseInt(txtModelId.getText());
                    int versionId = Integer.parseInt(txtVersionId.getText());
                    String vin = txtVIN.getText();
                    String engine = txtEngine.getText();

                    // 3. KHỞI TẠO ĐỐI TƯỢNG THEO PHÂN CẤP
                    MotorbikeModel model = new MotorbikeModel(modelId, txtName.getText(), txtBrand.getText(), txtType.getText(), txtDesc.getText());
                    MotorbikeVersion version = new MotorbikeVersion(versionId, txtColor.getText(), txtCapacity.getText(), txtPrice.getText(), model);
                    MotorbikeInstance instance = new MotorbikeInstance(vin, engine, version, importDate);
                    PurchaseOrder order = new PurchaseOrder(orderId, supplier, importDate);

                    // 4. THỰC THI QUA SERVICE
                    MotorImportService service = new MotorImportService();
                    
                    // Lưu Đơn nhập hàng trước
                    service.addPurchaseOrder(order);
                    
                    // Lưu thông tin xe (Model -> Version -> Instance)
                    service.addNewModel(model);
                    service.addNewVersion(version);
                    service.addNewMotorbike(instance);

                    JOptionPane.showMessageDialog(null, "Nhập kho thành công!\nĐơn hàng: " + orderId + "\nXe VIN: " + vin);
                    clearFields();

                } catch (java.time.format.DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Định dạng ngày phải là YYYY-MM-DD (Ví dụ: 2023-10-25)");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Các trường ID và Giá phải là số!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                }
            }
        });
    }

    private void clearFields() {
        txtVIN.setText("");
        txtEngine.setText("");
        // Giữ lại Order ID và NCC nếu người dùng muốn nhập nhiều xe cho cùng 1 đơn
    }
}