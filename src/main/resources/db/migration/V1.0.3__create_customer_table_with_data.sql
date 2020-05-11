use `istore`;

CREATE TABLE `customers` (
     `id` int auto_increment NOT NULL,
     `email` varchar(128) NOT NULL,
     `password` varchar(128) NOT NULL,
     `fullname` varchar(128) NOT NULL default ' ',
     `enabled` boolean NOT NULL default true,
     `role` varchar(16) NOT NULL default 'USER',
     PRIMARY KEY (`id`),
     UNIQUE INDEX `email_unique` (`email` ASC)
);

create table `customers_products` (
    `products_id` INT NOT NULL,
    `customers_id` INT NOT NULL,
    CONSTRAINT `fk_product__customer` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
    CONSTRAINT `fk_customer__product` FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`)
)