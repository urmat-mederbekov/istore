use `istore`;

create table `purchases` (
    `id` int auto_increment NOT NULL,
    `product_name` varchar(128) NOT NULL,
    `quantity` float not null,
    `price` float not null,
    `customer_id` int NOT NULL,
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
    PRIMARY KEY (`id`)
)