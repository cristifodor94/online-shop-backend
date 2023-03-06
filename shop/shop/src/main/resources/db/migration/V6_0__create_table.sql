CREATE TABLE IF NOT EXISTS stock (
  quantity INT NULL,
   product_id INT NOT NULL,
   location_id INT NOT NULL,
   CONSTRAINT pk_stock PRIMARY KEY (product_id, location_id)
);

ALTER TABLE stock ADD CONSTRAINT FK_STOCK_ON_LOCATION FOREIGN KEY (location_id) REFERENCES location (id);

ALTER TABLE stock ADD CONSTRAINT FK_STOCK_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);