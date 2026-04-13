-- ==========================================
-- XÓA BẢNG NẾU ĐÃ TỒN TẠI ĐỂ RESET
-- ==========================================
DROP TABLE IF EXISTS warranty_visits CASCADE;
DROP TABLE IF EXISTS warranty_books CASCADE;
DROP TABLE IF EXISTS sale_order_details CASCADE;
DROP TABLE IF EXISTS sale_orders CASCADE;
DROP TABLE IF EXISTS motorbike_instances CASCADE;
DROP TABLE IF EXISTS motorbike_versions CASCADE;
DROP TABLE IF EXISTS motorbike_models CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS purchase_orders CASCADE;

-- ==========================================
-- 1. TẠO BẢNG
-- ==========================================

-- Lưu thông tin chung của mẫu xe
CREATE TABLE motorbike_models (
    model_id INT PRIMARY KEY,
    model_name VARCHAR(100) NOT NULL,
    brand VARCHAR(50),
    type VARCHAR(50),
    description TEXT
);

-- Lưu các phiên bản màu sắc, phân khối của mẫu xe đó
CREATE TABLE motorbike_versions (
    version_id INT PRIMARY KEY,
    model_id INT REFERENCES motorbike_models(model_id) ON DELETE CASCADE,
    color VARCHAR(30),
    engine_capacity VARCHAR(20),
    price VARCHAR(50)
);

-- Lưu các đợt nhập hàng (PurchaseOrder)
CREATE TABLE purchase_orders (
    id INT PRIMARY KEY,
    supplier VARCHAR(100),
    order_date DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'IN_STOCK'
);

-- Lưu từng chiếc xe cụ thể trong kho (MotorbikeInstance)
CREATE TABLE motorbike_instances (
    vin VARCHAR(50) PRIMARY KEY,
    engine_number VARCHAR(50) UNIQUE,
    version_id INT REFERENCES motorbike_versions(version_id),
    import_date DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'IN_STOCK'
);

-- Quản lý khách hàng
CREATE TABLE customers (
    id INT PRIMARY KEY,
    identity_card VARCHAR(20) UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    phone VARCHAR(15),
    address TEXT,
    email VARCHAR(100)
);

-- Hóa đơn bán hàng
CREATE TABLE sale_orders (
    order_id INT PRIMARY KEY,
    customer_id INT REFERENCES customers(id),
    order_date DATE DEFAULT CURRENT_DATE,
    payment_status VARCHAR(20) DEFAULT 'PAID'
);

-- Chi tiết từng xe trong hóa đơn
CREATE TABLE sale_order_details (
    order_id INT REFERENCES sale_orders(order_id) ON DELETE CASCADE,
    vin VARCHAR(50) REFERENCES motorbike_instances(vin),
    sale_price INT
);

-- Sổ bảo hành
CREATE TABLE warranty_books (
    vin VARCHAR(50) PRIMARY KEY REFERENCES motorbike_instances(vin),
    customer_id INT REFERENCES customers(id),
    issue_date DATE,
    exp_date DATE
);

-- Lịch sử các lần đi bảo dưỡng
CREATE TABLE warranty_visits (
    id INT PRIMARY KEY,
    customerId INT REFERENCES customers(id),
    vin VARCHAR(50) REFERENCES warranty_books(vin),
    visit_date DATE,
    km_reading INT,
    technician_notes TEXT
);

-- ==========================================
-- 2. THÊM DỮ LIỆU TỪ MÃ JAVA CỦA BẠN
-- ==========================================

-- 1. Tạo hóa đơn nhập hàng (Purchase Orders)
INSERT INTO purchase_orders (id, supplier, order_date) VALUES 
(101, 'Honda Vietnam Factory', '2026-04-01'),
(102, 'Yamaha Japan Factory', '2026-04-02');

-- 2. Thêm Model
INSERT INTO motorbike_models (model_id, model_name, brand, type, description) VALUES
(1, 'Air Blade', 'Honda', 'Sport', 'Xe loai the thao'),
(2, 'Vision', 'Honda', 'Pho thong', 'Xe loai pho thong'),
(3, 'SH', 'Honda', 'Cao cap', 'Xe loai cao cap'),
(4, 'Sirius', 'Yamaha', 'Xe so', 'Xe pho thong gia re');

-- 3. Thêm Version
INSERT INTO motorbike_versions (version_id, model_id, color, engine_capacity, price) VALUES
(1001, 1, 'red', '130cc', '35000000'),
(1002, 1, 'white', '130cc', '46000000'),
(2001, 2, 'blue', '170cc', '70000000'),
(2002, 2, 'black', '210cc', '56000000'),
(3001, 3, 'white', '150cc', '110000000'),
(3002, 3, 'brown', '150cc', '105000000'),
(4001, 4, 'white', '110cc', '23000000'),
(4002, 4, 'black', '110cc', '22000000');

-- 4. Nhập xe vào kho (Motorbike Instances)
-- Lưu ý: Những xe nào đã bán qua SaleOrder mình sẽ set luôn trạng thái là 'SOLD' như trong code Java của bạn cập nhật
INSERT INTO motorbike_instances (vin, engine_number, version_id, import_date, status) VALUES
('VIN-AB-001', 'ENG-001', 1002, '2026-04-01', 'SOLD'),
('VIN-VS-002', 'ENG-002', 2001, '2026-04-01', 'SOLD'),
('VIN-SH-001', 'ESH-095', 3001, '2026-04-02', 'IN_STOCK'),
('VIN-SI-001', 'ESH-011', 4002, '2026-04-02', 'IN_STOCK');

-- 5. Thêm khách hàng (Customers)
INSERT INTO customers (id, identity_card, full_name, date_of_birth, phone, address, email) VALUES
(210, '079012345678', 'Trần Văn A', '1990-01-01', '0909123456', '123 Nguyen Trai, HN', 'trannv@example.com'),
(222, '0516653126516', 'Dang Bao', '1990-01-01', '0901234567', '123 Nguyen Trai, HN', 'dangbao@test.com');

-- 6. Bán xe và tạo hóa đơn (Sale Orders & Details)
INSERT INTO sale_orders (order_id, customer_id, order_date, payment_status) VALUES
(1, 210, '2026-04-06', 'PAID'),
(2, 222, '2026-04-07', 'PAID');

INSERT INTO sale_order_details (order_id, vin, sale_price) VALUES
(1, 'VIN-AB-001', 60000000),
(2, 'VIN-VS-002', 70000000);

-- 7. Tạo sổ bảo hành (Warranty Books)
-- Sử dụng CURRENT_DATE và hàm cộng năm trong PostgreSQL
INSERT INTO warranty_books (vin, customer_id, issue_date, exp_date) VALUES
('VIN-AB-001', 210, CURRENT_DATE, CURRENT_DATE + INTERVAL '2 years'),
('VIN-VS-002', 222, CURRENT_DATE, CURRENT_DATE + INTERVAL '2 years');

-- 8. Ghi nhận lịch sử bảo hành (Warranty Visits)
-- Lấy ngày hiện tại cộng thêm số tháng như trong mã Java (LocalDate.now().plusMonths)
INSERT INTO warranty_visits (id, customerId, vin, visit_date, km_reading, technician_notes) VALUES
(1001, 210, 'VIN-AB-001', CURRENT_DATE + INTERVAL '3 months', 1500, 'Error_0 - Thay nhớt miễn phí lần 1'),
(1002, 210, 'VIN-AB-001', CURRENT_DATE + INTERVAL '4 months', 3000, 'Error_2 - Thay thắng miễn phí lần 1');