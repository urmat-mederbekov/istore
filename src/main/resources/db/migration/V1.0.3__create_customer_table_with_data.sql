use `istore`;

create table `customers` (
    `id` INT auto_increment NOT NULL,
    `username` varchar(128) NOT NULL,
    `email` varchar(128) NOT NULL,
    `firstname` varchar(128) NOT NULL,
    `lastname` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);