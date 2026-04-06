package services;

import models.Customer;
import java.util.ArrayList;

public class CustomerService {
    // Danh sách lưu trữ tất cả khách hàng của HEAD
    private ArrayList<Customer> cusList = new ArrayList<>();

    // Hàm thêm khách hàng mới
    public void addCustomer(Customer newCus) {
        cusList.add(newCus);
    }

    // Hàm hiển thị danh sách khách hàng
    public void showCustomer() {
        for (Customer c : cusList) {
            System.out.println(c);
        }
    }

    // Hàm tìm kiếm khách hàng theo ID (bổ sung thêm để logic chặt chẽ hơn)
    public void findCustomerById(int id) {
        for (Customer c : cusList) {
            if (c.getId() == id) {
                c.showInforCustomer();
            }
        }
        System.out.println("Không tìm thấy khách hàng có ID: " + id); // Thông báo nếu không tìm thấy khách hàng
    }
}