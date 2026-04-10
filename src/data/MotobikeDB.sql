-- -- Cách an toàn nhất: Thêm CASCADE để nó tự xóa các liên kết liên quan
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
-- 1. NHÓM QUẢN LÝ DANH MỤC XE (Dùng cho MotorImportService)
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

-- ==========================================
-- 2. NHÓM QUẢN LÝ KHO & NHẬP HÀNG (Dùng cho MotorImportService & checkStatusWareHouse)
-- ==========================================

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
    version_id INT ,
    import_date DATE DEFAULT CURRENT_DATE,
    status VARCHAR(20) DEFAULT 'IN_STOCK' -- Để checkStatusWareHouse lọc
);

-- ==========================================
-- 3. NHÓM QUẢN LÝ KHÁCH HÀNG (Dùng cho CustomerService)
-- ==========================================

CREATE TABLE customers (
    id INT PRIMARY KEY,
    identity_card VARCHAR(20) UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    phone VARCHAR(15),
    address TEXT,
    email VARCHAR(100)
);

-- ==========================================
-- 4. NHÓM GIAO DỊCH & BẢO HÀNH
-- ==========================================

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