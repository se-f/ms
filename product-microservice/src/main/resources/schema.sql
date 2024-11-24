-- schema.sql
CREATE TABLE IF NOT EXISTS product (
                                       id INT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE
    );
