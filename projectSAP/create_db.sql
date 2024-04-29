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
    description TEXT
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
INSERT INTO Products (product_name, type, quantity, price, description)
VALUES
  -- Women's Perfumes
  ('Chanel No. 5', 'WOMEN', 100, 99.99, 'A timeless classic with notes of rose, jasmine, ylang-ylang, and sandalwood.'),
  ('Miss Dior', 'WOMEN', 80, 79.99, 'A sparkling floral fragrance with rose, mandarin, and patchouli.'),
  ('Black Opium', 'WOMEN', 60, 84.99, 'An intense and alluring scent with coffee, vanilla, and white florals.'),
  ('Heavenly Glow', 'WOMEN', 70, 89.99, 'A luminous floral bouquet with rose, jasmine, and magnolia.'),
  ('La Vie Est Belle', 'WOMEN', 50, 69.99, 'A sweet and gourmand fragrance with iris, patchouli, and vanilla.'),
  ('Lancôme La Vie Est Belle Eau de Toilette', 'WOMEN', 85, 84.99, 'A sparkling and fruity fragrance with pear, iris, and vanilla.'),
  ('Miss Dior Blooming Bouquet', 'WOMEN', 70, 69.99, 'A light and airy floral scent with peony, rose, and musk.'),
  ('Ariana Grande Cloud', 'WOMEN', 90, 59.99, 'A gourmand and playful fragrance with lavender, marshmallow, and pear.'),
  ('Gucci Bloom', 'WOMEN', 65, 99.99, 'A romantic and powdery fragrance with tuberose, jasmine, and orris root.'),
  ('Tom Ford Jasmin Rouge', 'WOMEN', 50, 119.99, 'A luxurious and seductive fragrance with jasmine, saffron, and vanilla.'),
  -- Men's Perfumes
  ('Acqua di Gio', 'MEN', 90, 74.99, 'A fresh and aquatic fragrance with citrus, marine notes, and woody base.'),
  ('Bleu de Chanel', 'MEN', 80, 89.99, 'A woody and aromatic scent with grapefruit, ginger, and cedarwood.'),
  ('Tom Ford Oud Wood', 'MEN', 50, 129.99, 'A rich and luxurious fragrance with oud wood, leather, and spices.'),
  ('Sauvage', 'MEN', 70, 94.99, 'A bold and spicy fragrance with bergamot, pepper, and ambroxan.'),
  ('Creed Aventus', 'MEN', 60, 109.99, 'A citrusy and fruity fragrance with pineapple, black currant, and birch.'),
  ('Dior Fahrenheit', 'MEN', 75, 89.99, 'A warm and spicy fragrance with mandarin, nutmeg, and leather.'),
  ('Acqua di Parma Colonia', 'MEN', 95, 79.99, 'A classic and refreshing citrus scent with lemon, grapefruit, and lavender.'),
  ('Tom Ford Noir', 'MEN', 60, 139.99, 'A mysterious and woody blend with iris, black pepper, and vetiver.'),
  ('Issey Miyake L\'Eau d\'Issey Pour Homme', 'MEN', 80, 64.99, 'A fresh and aquatic fragrance with yuzu, cypress, and vetiver.'),
  ('Armani Code', 'MEN', 70, 99.99, 'A sophisticated and warm scent with tonka bean, amber, and leather.')
;
INSERT INTO Employee (username, password, email, first_name, last_name, position_at_store, salary, contact_number)
VALUES ("MYovchev", "Kivim3456", "myovchev@per-store.com", "Martin", "Yovchev", "developer", 55000.00, "0891234567"),
("SMitev", "Hili1234", "stmitev@per-store.com", "Stanislav", "Mitev", "sys admin", 45000.00, "0897654321"),
    ("ETonev", "12Plok34", "etonev@per-store.com", "Etien", "Tonev", "developer", 35000.00, "0888864288");