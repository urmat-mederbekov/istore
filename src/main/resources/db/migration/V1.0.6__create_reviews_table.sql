use `istore`;

create table `reviews` (
    `id` int auto_increment NOT NULL,
    `product_id` int NOT NULL,
    `text` varchar(128) NOT NULL,
    `customer_id` int NOT NULL,
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    PRIMARY KEY (`id`)
)