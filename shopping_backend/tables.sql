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
