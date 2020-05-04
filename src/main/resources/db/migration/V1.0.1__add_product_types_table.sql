use `istore`;

create table `product_types` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `icon` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);

alter table `products`
add column `product_type_id` INT NOT NULL after `image`, add CONSTRAINT `fk_product	product_types`
FOREIGN KEY (`product_type_id`) REFERENCES `product_types` (`id`);

insert into `product_types` (`name`, `icon`) values
('IPhone', 'iPhone_11_Pro_Max_512_gb_1_sim.jpg'),
('IPad', 'iPad_Pro_12,9_dime_4G_1_tb.png'),
('Mac', 'MacBook_Pro_16_dime_Touch_Bar_(MVVM2).jpg'),
('AirPods', 'AirPods_Pro.jpg'),
('Apple Watch' , 'Apple_Watch_Stainless_Steel_Case.jpg');