CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

CREATE TABLE `internet_shop`.`users` (
    `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    `login` VARCHAR(225) NOT NULL,
    `password` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

CREATE TABLE `orders_products` (
    `order_id` bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `orders_id_fk_idx` (`order_id`),
    KEY `products_id_fk_idx` (`product_id`),
    CONSTRAINT `orders_products_orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `orders_products_products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
    `order_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY `orders_users_fk_idx` (`user_id`),
    CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
    `role_id` bigint NOT NULL AUTO_INCREMENT,
    `role_name` varchar(256) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `shopping_carts` (
    `cart_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`cart_id`),
    KEY `carts_users_fk_idx` (`user_id`),
    CONSTRAINT `user_id_key` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `shopping_carts_products` (
    `cart_id` bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `carts_products_carts_fk_idx` (`cart_id`),
    KEY `carts_products_products_fk_idx` (`product_id`),
    CONSTRAINT `carts_products_carts_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`),
    CONSTRAINT `carts_products_products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users_roles` (
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `users_roles_users_fk_idx` (`user_id`),
    KEY `users_roles_roles_fk_idx` (`role_id`),
    CONSTRAINT `users_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `users_roles_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO roles (role_id, role_name) VALUES (1, 'ADMIN'), (2, 'USER');