-- ### Code for the Database ### --
DROP DATABASE IF EXISTS OnlineStore;
CREATE DATABASE OnlineStore;
USE OnlineStore;

CREATE TABLE Employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,-- Security Hash Algorithm 2
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    position_at_store VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    contact_number VARCHAR(30) NOT NULL,
    CONSTRAINT check_email_length CHECK (CHAR_LENGTH(email) <= 100),
    CONSTRAINT check_first_name_length CHECK (CHAR_LENGTH(first_name) <= 50),
    CONSTRAINT check_last_name_length CHECK (CHAR_LENGTH(last_name) <= 50)
);
CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL, -- hashed password SHA2(str, hash_length(bits));
-- Security Hash Algorithm 2
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    loyalty_points INT DEFAULT 0
);

CREATE TABLE Address (
    address_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code INT NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
	CONSTRAINT check_address_line1_length CHECK (CHAR_LENGTH(address_line1) <= 255),
    CONSTRAINT check_address_line2_length CHECK (CHAR_LENGTH(address_line2) <= 255)
);

CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    type ENUM('MEN', 'WOMEN') NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    image_url VARCHAR(2000)
);
CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    customer_id INT NOT NULL,
    address_id INT NOT NULL,
    order_date TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id)
);
CREATE TABLE OrdersProducts (
    order_product_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
INSERT INTO Products (product_name, type, quantity, price, description, image_url)
VALUES
  -- Women's Perfumes
  ('Chanel No. 5', 'WOMEN', 100, 99.99, 'A timeless classic with notes of rose, jasmine, ylang-ylang, and sandalwood.', 'https://www.sephora.com/productimages/sku/s43687-main-zoom.jpg'),
  ('Miss Dior', 'WOMEN', 80, 79.99, 'A sparkling floral fragrance with rose, mandarin, and patchouli.', 'https://www.koku.bg/buxus/images/products/3327838_6615641640171321.jpg'),
  ('Black Opium', 'WOMEN', 60, 84.99, 'An intense and alluring scent with coffee, vanilla, and white florals.', 'https://douglas.bg/media/catalog/product/cache/5fce787bab7c2c5339535a641e81e5aa/1/_/1_3321_1.jpg'),
  ('Heavenly Glow', 'WOMEN', 70, 89.99, 'A luminous floral bouquet with rose, jasmine, and magnolia.', 'https://es.victoriassecret.com/p/874x1165/tif/13/b1/13b11a40cbee455c86618dd963fa4db9/112144783872_OM_B.jpg'),
  ('La Vie Est Belle', 'WOMEN', 50, 69.99, 'A sweet and gourmand fragrance with iris, patchouli, and vanilla.', 'https://douglas.bg/media/catalog/product/cache/b898e2a9afdff76fada0035d7a852be8/1/_/1_4590.jpg'),
  ('Lancôme La Vie Est Belle Eau de Toilette', 'WOMEN', 85, 84.99, 'A sparkling and fruity fragrance with pear, iris, and vanilla.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThhma5oS7o1pR6je9njUFzR3bRhJ8ZlKbJrx5JbQYBKg&s'),
  ('Miss Dior Blooming Bouquet', 'WOMEN', 70, 69.99, 'A light and airy floral scent with peony, rose, and musk.', 'https://parfium.bg/public/img/products/57784/big_christian-dior-miss-dior-blooming-bouquet-parfyum-za-jeni-bez-opakovka-edt-5778414977.jpg'),
  ('Ariana Grande Cloud', 'WOMEN', 90, 59.99, 'A gourmand and playful fragrance with lavender, marshmallow, and pear.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcHqyXhruZIXnbCSAFC1qTlQ2xJC7FWplhb2DONP6j8w&s'),
  ('Gucci Bloom', 'WOMEN', 65, 99.99, 'A romantic and powdery fragrance with tuberose, jasmine, and orris root.', 'https://douglas.bg/media/catalog/product/cache/5fce787bab7c2c5339535a641e81e5aa/1/_/1_6289_1.jpg'),
  -- Men's Perfumes
  ('Acqua di Gio', 'MEN', 90, 74.99, 'A fresh and aquatic fragrance with citrus, marine notes, and woody base.', 'https://www.koku.bg/buxus/images/products/3364428_giorgio-armani-code-plnitelny-edt-75ml-tester.jpg'),
  ('Creed Aventus', 'MEN', 60, 109.99, 'A citrusy and fruity fragrance with pineapple, black currant, and birch.', 'https://essenza-nobile.de/media/image/product/240728/md/creed-aventus-en.jpg'),
  ('Dior Fahrenheit', 'MEN', 75, 89.99, 'A warm and spicy fragrance with mandarin, nutmeg, and leather.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsqYccTtWrS2gDLXD2cRK9MZrGgN61FumGewtZUQXcKg&s'),
  ('Acqua di Parma Colonia', 'MEN', 95, 79.99, 'A classic and refreshing citrus scent with lemon, grapefruit, and lavender.', 'https://static.beautytocare.com/media/catalog/product/a/c/acqua-di-parma-colonia-eau-de-cologne-180ml.jpg'),
  ('Tom Ford Noir', 'MEN', 60, 139.99, 'A mysterious and woody blend with iris, black pepper, and vetiver.', 'https://douglas.bg/media/catalog/product/cache/5fce787bab7c2c5339535a641e81e5aa/1/_/1_4649.jpg'),
  ('Issey Miyake L\'Eau d\'Issey Pour Homme', 'MEN', 80, 64.99, 'A fresh and aquatic fragrance with yuzu, cypress, and vetiver.', 'https://www.parfimo.bg/data/cache/thumb_min500_max1000-min500_max1000-12/products/42730/1530453024/issey-miyake-l-eau-d-issey-pour-homme-toaletna-voda-dla-mezczyzn-200-ml-233198.jpg')
;
INSERT INTO Employee (username, password, email, first_name, last_name, position_at_store, salary, contact_number)
VALUES ("MYovchev", "Kivim3456", "myovchev@per-store.com", "Martin", "Yovchev", "developer", 55000.00, "0891234567"),
("SMitev", "Hili1234", "stmitev@per-store.com", "Stanislav", "Mitev", "sys admin", 45000.00, "0897654321"),
    ("ETonev", "12Plok34", "etonev@per-store.com", "Etien", "Tonev", "developer", 35000.00, "0888864288");