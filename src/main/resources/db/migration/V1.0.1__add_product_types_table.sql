use `istore`;

create table `product_types` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);

alter table `products`
add column `product_type_id` INT NOT NULL after `image`, add CONSTRAINT `fk_product	product_types`
FOREIGN KEY (`product_type_id`) REFERENCES `product_types` (`id`);

insert into `product_types` (`name`) values ('IPhone'), ('IPad'), ('Mac'), ('AirPods'), ('Apple Watch');