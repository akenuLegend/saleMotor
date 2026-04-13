package views;

import models.Customer;
import services.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CustomerPanel extends JPanel {
    private JTextField txtCustomerId;
    private JTextField txtFullName;
    private JTextField txtIdentityCard;
    private JTextField txtDateOfBirth;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddress;
    
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;

    // Khai báo Service để thao tác với DB
    private CustomerService customerService;

    public CustomerPanel() {
        customerService = new CustomerService(); // Khởi tạo service

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // ==========================================
        // 1. PHẦN FORM NHẬP LIỆU (TOP)
        // ==========================================
        JPanel formPanel = new JPanel(new GridLayout(4, 4, 15, 15));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Khách Hàng"));
        
        formPanel.add(new JLabel("Mã Khách Hàng:"));
        txtCustomerId = new JTextField();
        formPanel.add(txtCustomerId);
        
        formPanel.add(new JLabel("Tên Khách Hàng:"));
        txtFullName = new JTextField();
        formPanel.add(txtFullName);
        
        formPanel.add(new JLabel("CMND/CCCD:"));
        txtIdentityCard = new JTextField();
        formPanel.add(txtIdentityCard);
        
        formPanel.add(new JLabel("Ngày Sinh (YYYY-MM-DD):"));
        txtDateOfBirth = new JTextField();
        formPanel.add(txtDateOfBirth);
        
        formPanel.add(new JLabel("Số Điện Thoại:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);
        
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
        formPanel.add(new JLabel("Địa Chỉ:"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);
        
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        
        // ==========================================
        // 2. PHẦN NÚT CHỨC NĂNG
        // ==========================================
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnAdd = new JButton("Thêm Mới");
        btnUpdate = new JButton("Cập Nhật");
        btnDelete = new JButton("Xóa");
        btnClear = new JButton("Làm Mới");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        
        // ==========================================
        // 3. PHẦN BẢNG HIỂN THỊ DỮ LIỆU (CENTER)
        // ==========================================
        String[] columns = {
            "Mã KH", "Tên Khách Hàng", "CMND/CCCD", 
            "Ngày Sinh", "Số Điện Thoại", "Email", "Địa Chỉ"
        };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa trực tiếp trên bảng
            }
        };
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Khách Hàng"));
        
        add(scrollPane, BorderLayout.CENTER);

        // Khởi tạo các sự kiện và load dữ liệu ban đầu
        initEvents();
        loadDataToTable(); // Đổ dữ liệu từ DB lên bảng khi vừa mở
    }

    // ==========================================
    // CÁC HÀM XỬ LÝ LOGIC CHÍNH
    // ==========================================

    private void loadDataToTable() {
        tableModel.setRowCount(0); // Xóa bảng
        try {
            List<Customer> customers = customerService.getAllCustomers(); 
            for (Customer c : customers) {
                tableModel.addRow(new Object[]{
                    c.getId(), // id là int
                    c.getFullName(), 
                    c.getIdentityCard(), 
                    c.getDateOfBirth(), 
                    c.getPhone(), 
                    c.getEmail(), 
                    c.getAddress()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private Customer getCustomerFromForm() {
        if (txtCustomerId.getText().trim().isEmpty() || txtFullName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Khách Hàng và Tên không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            int id = Integer.parseInt(txtCustomerId.getText().trim());
            LocalDate dob = LocalDate.parse(txtDateOfBirth.getText().trim());
            
            return new Customer(
                txtFullName.getText().trim(),
                txtPhone.getText().trim(),
                id,
                txtIdentityCard.getText().trim(),
                dob,
                txtAddress.getText().trim(),
                txtEmail.getText().trim()
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải là SỐ NGUYÊN!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh sai định dạng! Vui lòng nhập YYYY-MM-DD", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    private void initEvents() {
        // --- LÀM MỚI ---
        btnClear.addActionListener(e -> {
            txtCustomerId.setText(""); txtFullName.setText(""); txtIdentityCard.setText("");
            txtDateOfBirth.setText(""); txtPhone.setText(""); txtEmail.setText(""); txtAddress.setText("");
            txtCustomerId.setEditable(true); txtCustomerId.requestFocus();
        });
        
        // --- THÊM ---
        btnAdd.addActionListener(e -> {
            Customer customer = getCustomerFromForm();
            if (customer != null) {
                if (customerService.addCustomerGUI(customer)) {
                    JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
                    loadDataToTable();
                    btnClear.doClick(); // Kích hoạt nút làm mới
                } else {
                    JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- CẬP NHẬT ---
        btnUpdate.addActionListener(e -> {
            Customer customer = getCustomerFromForm();
            if (customer != null) {
                if (customerService.updateCustomerGUI(customer)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
                    loadDataToTable();
                    btnClear.doClick();
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- XÓA ---
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng trên bảng để xóa!");
                return;
            }
            
            int customerId = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa khách hàng ID: " + customerId + " không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (customerService.deleteCustomerGUI(customerId)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
                    loadDataToTable();
                    btnClear.doClick();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa! Có thể KH này đang có lịch sử mua hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // --- CLICK VÀO BẢNG ĐỔ LÊN FORM ---
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                txtCustomerId.setText(tableModel.getValueAt(row, 0).toString());
                txtFullName.setText(tableModel.getValueAt(row, 1).toString());
                txtIdentityCard.setText(tableModel.getValueAt(row, 2) != null ? tableModel.getValueAt(row, 2).toString() : "");
                txtDateOfBirth.setText(tableModel.getValueAt(row, 3) != null ? tableModel.getValueAt(row, 3).toString() : "");
                txtPhone.setText(tableModel.getValueAt(row, 4) != null ? tableModel.getValueAt(row, 4).toString() : "");
                txtEmail.setText(tableModel.getValueAt(row, 5) != null ? tableModel.getValueAt(row, 5).toString() : "");
                txtAddress.setText(tableModel.getValueAt(row, 6) != null ? tableModel.getValueAt(row, 6).toString() : "");
                
                txtCustomerId.setEditable(false); // Khóa ID khi đang xem/sửa
            }
        });
    }
}