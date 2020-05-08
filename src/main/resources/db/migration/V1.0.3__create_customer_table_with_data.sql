use `istore`;

create table `customers` (
    `id` INT auto_increment NOT NULL,
    `username` varchar(128) NOT NULL,
    `email` varchar(128) NOT NULL,
    `firstname` varchar(128) NOT NULL,
    `lastname` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);

create table `customers_products` (
    `products_id` INT NOT NULL,
    `customers_id` INT NOT NULL,
    CONSTRAINT `fk_product__customer` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
    CONSTRAINT `fk_customer__product` FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`)
)