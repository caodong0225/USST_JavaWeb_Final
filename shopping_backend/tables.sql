-- 商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(100) NOT NULL COMMENT '商品名称',
    `description` TEXT COMMENT '商品描述',
    `price` DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '库存',
    `image_path` VARCHAR(255) COMMENT '商品图片存储路径',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 订单表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `goods_id` INT NOT NULL COMMENT '商品ID',
    `quantity` INT NOT NULL DEFAULT 1 COMMENT '购买数量',
    `total_price` DECIMAL(10, 2) NOT NULL COMMENT '总价',
    `order_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`goods_id`) REFERENCES `goods`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `order_id` INT NOT NULL COMMENT '订单ID',
    `goods_id` INT NOT NULL COMMENT '商品ID',
    `quantity` INT NOT NULL DEFAULT 1 COMMENT '商品数量',
    `total_price` DECIMAL(10, 2) NOT NULL COMMENT '商品总价',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`goods_id`) REFERENCES `goods`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';


DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `goods_id` INT NOT NULL COMMENT '商品ID',
    `quantity` INT NOT NULL DEFAULT 1 COMMENT '商品数量',
    `user_id` INT NOT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`goods_id`) REFERENCES `goods`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';


INSERT INTO `goods` (`name`, `description`, `price`, `stock`, `image_path`) VALUES
('智能手机', '高性能的智能手机，适合日常使用', 6999.00, 100, '/goods/smartphone.avif'),
('笔记本电脑', '轻薄便携的办公笔记本', 4699.00, 50, '/goods/laptop.avif'),
('无线耳机', '高品质音效的蓝牙无线耳机', 299.00, 200, '/goods/earphones.avif'),
('智能手表', '监测健康数据的智能手表', 249.00, 150, '/goods/smartwatch.avif'),
('台灯', '适合阅读的护眼LED台灯', 189.00, 500, '/goods/lamp.avif');
