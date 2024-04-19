-- ### Code for the Database ### --
DROP DATABASE IF EXISTS OnlineStore;
CREATE DATABASE OnlineStore;
USE OnlineStore;

CREATE TABLE Employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
-- Security Hash Algorithm 2
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    position VARCHAR(100) NOT NULL,
    salary DOUBLE NOT NULL,
    contact_number VARCHAR(30) NOT NULL
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
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255)
);

CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    type ENUM('men', 'women') NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
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
    total_price DOUBLE NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

INSERT INTO Products (product_name, type, quantity, price, description)
VALUES

  -- Women's Perfumes
  ('Chanel No. 5', 'women', 100, 99.99, 'A timeless classic with notes of rose, jasmine, ylang-ylang, and sandalwood.'),
  ('Miss Dior', 'women', 80, 79.99, 'A sparkling floral fragrance with rose, mandarin, and patchouli.'),
  ('Black Opium', 'women', 60, 84.99, 'An intense and alluring scent with coffee, vanilla, and white florals.'),
  ('Heavenly Glow', 'women', 70, 89.99, 'A luminous floral bouquet with rose, jasmine, and magnolia.'),
  ('La Vie Est Belle', 'women', 50, 69.99, 'A sweet and gourmand fragrance with iris, patchouli, and vanilla.'),
  ('Lancôme La Vie Est Belle Eau de Toilette', 'women', 85, 84.99, 'A sparkling and fruity fragrance with pear, iris, and vanilla.'),
  ('Miss Dior Blooming Bouquet', 'women', 70, 69.99, 'A light and airy floral scent with peony, rose, and musk.'),
  ('Ariana Grande Cloud', 'women', 90, 59.99, 'A gourmand and playful fragrance with lavender, marshmallow, and pear.'),
  ('Gucci Bloom', 'women', 65, 99.99, 'A romantic and powdery fragrance with tuberose, jasmine, and orris root.'),
  ('Tom Ford Jasmin Rouge', 'women', 50, 119.99, 'A luxurious and seductive fragrance with jasmine, saffron, and vanilla.'),


  -- Men's Perfumes
  ('Acqua di Gio', 'men', 90, 74.99, 'A fresh and aquatic fragrance with citrus, marine notes, and woody base.'),
  ('Bleu de Chanel', 'men', 80, 89.99, 'A woody and aromatic scent with grapefruit, ginger, and cedarwood.'),
  ('Tom Ford Oud Wood', 'men', 50, 129.99, 'A rich and luxurious fragrance with oud wood, leather, and spices.'),
  ('Sauvage', 'men', 70, 94.99, 'A bold and spicy fragrance with bergamot, pepper, and ambroxan.'),
  ('Creed Aventus', 'men', 60, 109.99, 'A citrusy and fruity fragrance with pineapple, black currant, and birch.'),
  ('Dior Fahrenheit', 'men', 75, 89.99, 'A warm and spicy fragrance with mandarin, nutmeg, and leather.'),
  ('Acqua di Parma Colonia', 'men', 95, 79.99, 'A classic and refreshing citrus scent with lemon, grapefruit, and lavender.'),
  ('Tom Ford Noir', 'men', 60, 139.99, 'A mysterious and woody blend with iris, black pepper, and vetiver.'),
  ('Issey Miyake L\'Eau d\'Issey Pour Homme', 'men', 80, 64.99, 'A fresh and aquatic fragrance with yuzu, cypress, and vetiver.'),
  ('Armani Code', 'men', 70, 99.99, 'A sophisticated and warm scent with tonka bean, amber, and leather.')
;

INSERT into employee (username, password, email, first_name, last_name, position, salary, contact_number)
VALUES ("MYovchev", "Kivim3456", "myovchev@per-store.com", "Martin", "Yovchev", "developer", 55000.00, "0891234567"),
("SMitev", "Hili1234", "stmitev@per-store.com", "Stanislav", "Mitev", "sys admin", 45000.00, "0897654321"),
    ("ETonev", "12Plok34", "etonev@per-store.com", "Etien", "Tonev", "developer", 35000.00, "0888864288");