CREATE TABLE product_table(
      product_no INT NOT NULL AUTO_INCREMENT,
      product_name VARCHAR(120) NOT NULL,
      product_price INT NOT NULL,
      product_spec json NOT NULL,
      product_brand VARCHAR(30) NOT NULL,
      product_type VARCHAR(12) NOT NULL,
      PRIMARY KEY (product_no)
);

CREATE TABLE user_table(
    user_no INT AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL UNIQUE,
    user_pw VARCHAR(64) NOT NULL,
    admin BOOLEAN NOT NULL,
    PRIMARY KEY (user_no)
);

CREATE TABLE build_table(
    build_no INT AUTO_INCREMENT,
    user_no INT REFERENCES user_table(user_no),
    cpu_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    gpu_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    ram_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    storage_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    motherboard_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    cooler_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    case_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    etc_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    PRIMARY KEY (build_no)
);

CREATE TABLE build_post_table(
    post_no INT AUTO_INCREMENT,
    user_no INT REFERENCES user_table(user_no),
    build_no INT REFERENCES build_table(build_no),
    title VARCHAR(60) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    date DATETIME DEFAULT now(),
    PRIMARY KEY (post_no)
);