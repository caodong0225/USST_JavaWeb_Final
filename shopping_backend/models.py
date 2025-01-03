from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

# 商品表模型
class Goods(db.Model):
    __tablename__ = 'goods'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(100), nullable=False, comment='商品名称')
    description = db.Column(db.Text, comment='商品描述')
    price = db.Column(db.Numeric(10, 2), nullable=False, default=0.00, comment='商品价格')
    stock = db.Column(db.Integer, nullable=False, default=0, comment='库存')
    image_path = db.Column(db.String(255), comment='商品图片存储路径')
    category = db.Column(db.String(50), comment='商品类别')

# 购物车表模型
class Cart(db.Model):
    __tablename__ = 'cart'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    goods_id = db.Column(db.Integer, db.ForeignKey('goods.id'), nullable=False, comment='商品ID')
    quantity = db.Column(db.Integer, nullable=False, default=1, comment='商品数量')
    user_id = db.Column(db.Integer, nullable=False, comment='用户ID')  # 假设用户系统存在

    goods = db.relationship('Goods', backref=db.backref('cart_items', lazy=True))

# 订单表模型
class Orders(db.Model):
    __tablename__ = 'orders'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(db.Integer, nullable=False, comment='用户ID')  # 假设用户系统存在
    status = db.Column(db.Integer, nullable=False, default=1, comment='订单状态')  # 1:待付款, 2:已付款, etc.
    order_time = db.Column(db.DateTime, server_default=db.func.now(), comment='下单时间')

# 订单商品表模型，关联订单表和商品表
class OrderItems(db.Model):
    __tablename__ = 'order_items'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    order_id = db.Column(db.Integer, db.ForeignKey('orders.id'), nullable=False, comment='订单ID')
    goods_id = db.Column(db.Integer, db.ForeignKey('goods.id'), nullable=False, comment='商品ID')
    quantity = db.Column(db.Integer, nullable=False, default=1, comment='商品数量')
    total_price = db.Column(db.Numeric(10, 2), nullable=False, comment='商品总价')

    order = db.relationship('Orders', backref=db.backref('order_items', lazy=True))
    goods = db.relationship('Goods', backref=db.backref('order_goods', lazy=True))
