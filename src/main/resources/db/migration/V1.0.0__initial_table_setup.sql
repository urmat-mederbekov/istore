use `istore`;

create table `products` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `description` varchar(500) NOT NULL,
    `image` varchar(128) NOT NULL,
    `quantity` int NOT NULL,
    `price` float not null, PRIMARY KEY (`id`)
);