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

create table `carts` (
    `id` int auto_increment NOT NULL,
    `product_name` varchar(128) NOT NULL,
    `quantity` float not null,
    `price` float not null,
    `customer_id` int NOT NULL,
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
    PRIMARY KEY (`id`)
)