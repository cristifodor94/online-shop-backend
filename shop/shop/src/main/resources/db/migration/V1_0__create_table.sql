CREATE TABLE customer (
  id INT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   username VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   email_address VARCHAR(255) NULL,
   CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE location (
  id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   country VARCHAR(255) NULL,
   city VARCHAR(255) NULL,
   county VARCHAR(255) NULL,
   street VARCHAR(255) NULL,
   CONSTRAINT pk_location PRIMARY KEY (id)
);

CREATE TABLE product_category (
  id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   CONSTRAINT pk_productcategory PRIMARY KEY (id)
);

CREATE TABLE product (
  id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   price DECIMAL NULL,
   weight DOUBLE NULL,
   img_url VARCHAR(255) NULL,
   supplier_id INT NULL,
   product_category_id INT NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE order_detail (
  id INT AUTO_INCREMENT NOT NULL,
   order_id INT NULL,
   product_id INT NULL,
   quantity INT NULL,
   CONSTRAINT pk_order_detail PRIMARY KEY (id)
);

CREATE TABLE product_order (
  id INT AUTO_INCREMENT NOT NULL,
   created datetime NULL,
   country VARCHAR(255) NULL,
   city VARCHAR(255) NULL,
   county VARCHAR(255) NULL,
   street VARCHAR(255) NULL,
   shipped_from_id INT NULL,
   customer_id INT NULL,
   CONSTRAINT pk_product_order PRIMARY KEY (id)
);

CREATE TABLE stock (
  id INT AUTO_INCREMENT NOT NULL,
   product_id INT NULL,
   location_id INT NULL,
   quantity INT NULL,
   CONSTRAINT pk_stock PRIMARY KEY (id)
);

CREATE TABLE supplier (
  id INT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_supplier PRIMARY KEY (id)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_PRODUCTCATEGORY FOREIGN KEY (product_category_id) REFERENCES product_category (id);
ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES supplier (id);
ALTER TABLE order_detail ADD CONSTRAINT FK_ORDER_DETAIL_ON_ORDER FOREIGN KEY (order_id) REFERENCES product_order (id);
ALTER TABLE order_detail ADD CONSTRAINT FK_ORDER_DETAIL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE product_order ADD CONSTRAINT FK_PRODUCT_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);
ALTER TABLE product_order ADD CONSTRAINT FK_PRODUCT_ORDER_ON_SHIPPED_FROM FOREIGN KEY (shipped_from_id) REFERENCES location (id);
ALTER TABLE stock ADD CONSTRAINT FK_STOCK_ON_LOCATION FOREIGN KEY (location_id) REFERENCES location (id);
ALTER TABLE stock ADD CONSTRAINT FK_STOCK_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);


